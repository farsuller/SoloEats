package com.solodemo.supabase.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Reel(
    val id: Int,
    @SerialName("name") val name: String? = null,
    @SerialName("food_image") val foodImage: String? = null,
    @SerialName("is_reviewed") val isReviewed: Boolean? = null,
)