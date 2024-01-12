package com.solodemo.main.model

data class Food(
    val foodId: Int = 0,
    val foodName: String = "",
    val foodDescription: String = "",
    val foodImage: String = "",
    val price: String = "0.00",
    val starReview: Int = 1
)