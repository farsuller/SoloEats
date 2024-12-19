package com.solodemo.database.domain.usecase

import com.solodemo.database.domain.usecase.cart.DeleteAllCartItem
import com.solodemo.database.domain.usecase.cart.DeleteCartItemById
import com.solodemo.database.domain.usecase.cart.GetCartList
import com.solodemo.database.domain.usecase.cart.UpsertCart

data class CartUseCases(
    val getCartList: GetCartList,
    val upsertCart: UpsertCart,
    val deleteCartItemById: DeleteCartItemById,
    val deleteAllCartItem: DeleteAllCartItem,
)
