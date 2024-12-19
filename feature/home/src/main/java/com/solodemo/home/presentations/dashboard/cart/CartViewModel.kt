package com.solodemo.home.presentations.dashboard.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solodemo.database.domain.model.Cart
import com.solodemo.database.domain.usecase.CartUseCases
import com.solodemo.network.data.ApiResult
import com.solodemo.network.domain.usecase.EatsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCases: CartUseCases,
    private val eatsUseCases: EatsUseCases,
) : ViewModel() {

    private val _cartState = MutableStateFlow(CartState())
    val cartState: StateFlow<CartState> = _cartState.asStateFlow()

    private val deliveryFee = 59.0

    private val _isLoadingData = MutableStateFlow(false)
    val isLoadingData = _isLoadingData
        .onStart { requestApis() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), false)

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

    private fun requestApis() {
        _isLoadingData.value = true
        getCartList()
        getCoupons()
        _isLoadingData.value = false
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

    private fun getCoupons() = viewModelScope.launch {
        eatsUseCases.getCoupons()
            .onStart { _cartState.update { it.copy(isLoading = true) } }
            .catch { e -> _cartState.update { it.copy(errorMessage = e.message, isLoading = false) } }
            .collectLatest { response ->
                when (response) {
                    is ApiResult.Success -> {
                        _cartState.update {
                            it.copy(couponsList = response.result.data, isLoading = false)
                        }
                    }

                    is ApiResult.Error -> { _cartState.update { it.copy(errorMessage = response.message, isLoading = false) } }
                }
            }
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
