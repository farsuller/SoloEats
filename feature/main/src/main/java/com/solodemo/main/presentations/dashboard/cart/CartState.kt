package com.solodemo.main.presentations.dashboard.cart

import com.solodemo.database.domain.model.Cart

data class CartState(
    val cartList: List<Cart>? = null,
    val subTotalPrice: Double = 0.0,
    val totalPrice: Double = 0.0,
    val deliveryFee: Double = 0.0,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
