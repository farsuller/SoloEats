package com.solodemo.main.model

data class FoodCategory(
    val categoryId: Int,
    val categoryName: String,
    val categoryDescription: String,
    val foods: List<Food>,
)
