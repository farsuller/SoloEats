package com.solodemo.network.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Menu(
    val id: Int,

    @SerialName("menu_name")
    val menuName: String,

    @SerialName("menu_image")
    val menuImage: String,

    @SerialName("is_available")
    val isAvailable: Boolean,
)
