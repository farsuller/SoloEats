package com.solodemo.network.domain.usecase

data class EatsUseCases(
    val getMenus: GetMenus,
    val getReviews: GetReviews,
    val getCoupons: GetCoupons,
    val getProducts: GetProducts,
)
