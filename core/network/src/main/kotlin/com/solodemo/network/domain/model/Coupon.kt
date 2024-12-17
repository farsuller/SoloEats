package com.solodemo.network.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Coupon(
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    @SerialName("image_path")
    val imagePath: String? = null,
)
