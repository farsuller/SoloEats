package com.solodemo.main.model

data class FoodProduct(
    val foodCategories: List<FoodCategory>,
    val error: Any,
    val message: String,
    val status: Boolean
)