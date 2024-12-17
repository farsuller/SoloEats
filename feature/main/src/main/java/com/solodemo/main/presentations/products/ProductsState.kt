package com.solodemo.main.presentations.products

import com.solodemo.network.domain.model.FoodCategory

data class ProductsState(
    val productsList: List<FoodCategory>? = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
