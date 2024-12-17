package com.solodemo.network.data.remote.dto

import com.solodemo.network.domain.model.Menu
import kotlinx.serialization.Serializable

@Serializable
data class MenusResponse(
    val data: List<Menu>,
)
