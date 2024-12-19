package com.solodemo.network.data.remote.dto

import com.solodemo.network.domain.model.FoodCategory
import kotlinx.serialization.Serializable

@Serializable
data class ProductsResponse(
    val data: List<FoodCategory>,
)
