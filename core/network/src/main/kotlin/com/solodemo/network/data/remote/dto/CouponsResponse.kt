package com.solodemo.network.data.remote.dto

import com.solodemo.network.domain.model.Coupon
import kotlinx.serialization.Serializable

@Serializable
data class CouponsResponse(
    val data: List<Coupon>,
)
