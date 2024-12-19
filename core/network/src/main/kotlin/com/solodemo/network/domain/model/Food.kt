package com.solodemo.network.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Food(
    val id: Int = 0,
    val name: String? = null,
    val description: String? = null,
    val image: String? = null,
    val price: String? = null,
    val starReview: Int? = null,
)
