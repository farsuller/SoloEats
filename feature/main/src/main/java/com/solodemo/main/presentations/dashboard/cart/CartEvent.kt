package com.solodemo.main.presentations.dashboard.cart

import com.solodemo.database.domain.model.Cart

sealed class CartEvent {
    data class DeleteCartItem(val cartItem: Cart) : CartEvent()
    data class UpdateCartQuantity(val cartItem: Cart, val newQuantity: Int) : CartEvent()
    data object DeleteAllCartItem : CartEvent()
    data class UpsertCartItem(val cartItem: Cart) : CartEvent()
}
