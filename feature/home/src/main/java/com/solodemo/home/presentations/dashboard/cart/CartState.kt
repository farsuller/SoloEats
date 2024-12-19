package com.solodemo.home.presentations.dashboard.cart

import com.solodemo.database.domain.model.Cart
import com.solodemo.network.domain.model.Coupon

data class CartState(
    val cartList: List<Cart>? = null,
    val subTotalPrice: Double = 0.0,
    val totalPrice: Double = 0.0,
    val deliveryFee: Double = 0.0,
    val couponsList: List<Coupon> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
