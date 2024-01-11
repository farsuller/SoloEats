package com.solodemo.supabase.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val id: Int,
    @SerialName("name") val name: String? = null,
    @SerialName("profile_image") val profileImage: String? = null,
    @SerialName("review_text") val reviewText: String? = null,
    @SerialName("review_star") val reviewStar: Int,
)