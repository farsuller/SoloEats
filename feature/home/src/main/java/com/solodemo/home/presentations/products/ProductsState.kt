package com.solodemo.home.presentations.products

import com.solodemo.network.domain.model.FoodCategory

data class ProductsState(
    val productsList: List<FoodCategory>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
