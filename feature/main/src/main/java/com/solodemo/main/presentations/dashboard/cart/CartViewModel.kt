package com.solodemo.main.presentations.dashboard.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solodemo.database.domain.model.Cart
import com.solodemo.database.domain.usecase.CartUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCases: CartUseCases,
) : ViewModel() {

    private val _cartState = MutableStateFlow(CartState())
    val cartState: StateFlow<CartState> = _cartState.asStateFlow()

    private val deliveryFee = 59.0

    init {
        getCartList()
    }

    fun onEvent(event: CartEvent) {
        when (event) {
            is CartEvent.UpsertCartItem -> {
                upsertCartItem(event.cartItem)
            }

            is CartEvent.UpdateCartQuantity -> {
                updateCartQuantity(event.cartItem, event.newQuantity)
            }

            is CartEvent.DeleteCartItem -> {
                deleteCartItem(event.cartItem.id)
            }

            is CartEvent.DeleteAllCartItem -> {
                deleteAllCartItem()
            }
        }
    }

    private fun getCartList() {
        cartUseCases.getCartList().onEach { item ->
            val subTotal = item.sumOf { it.productDetails?.price?.toDouble() ?: 0.0 }

            _cartState.update {
                it.copy(
                    cartList = item.asReversed(),
                    deliveryFee = deliveryFee,
                    subTotalPrice = subTotal,
                    totalPrice = subTotal + deliveryFee,
                )
            }
        }.launchIn(viewModelScope)
    }
    private fun updateCartQuantity(cart: Cart, newQuantity: Int) = viewModelScope.launch {
        val updatedCart = cart.copy(
            productDetails = cart.productDetails?.copy(
                quantity = newQuantity,
                price = (cart.productDetails?.originalPrice?.toDouble()?.times(newQuantity))?.toString(),
            ),
        )
        upsertCartItem(updatedCart)
    }

    private fun upsertCartItem(cart: Cart) = viewModelScope.launch(Dispatchers.IO) {
        cartUseCases.upsertCart(cart = cart)
    }

    private fun deleteCartItem(itemById: Int) = viewModelScope.launch(Dispatchers.IO) {
        cartUseCases.deleteCartItemById(deleteItemById = itemById)
    }

    private fun deleteAllCartItem() = viewModelScope.launch(Dispatchers.IO) {
        cartUseCases.deleteAllCartItem()
    }
}
