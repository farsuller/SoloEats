package com.solodemo.network.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("profile_image")
    val profileImage: String,

    @SerialName("review_text")
    val reviewText: String,

    @SerialName("review_star")
    val reviewStar: Int,
)
