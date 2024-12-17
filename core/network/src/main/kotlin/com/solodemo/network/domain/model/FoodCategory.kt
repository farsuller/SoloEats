package com.solodemo.network.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class FoodCategory(
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val foods: List<Food>? = null,
)
