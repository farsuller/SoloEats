package com.solodemo.supabase.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Menu(
    val id: Int,
    @SerialName("menu_name") val menuName: String? = null,
    @SerialName("menu_image") val menuImage: String? = null,
    @SerialName("is_available") val isAvailable: Boolean,
)
