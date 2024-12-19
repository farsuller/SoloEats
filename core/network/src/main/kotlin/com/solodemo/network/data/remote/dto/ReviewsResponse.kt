package com.solodemo.network.data.remote.dto

import com.solodemo.network.domain.model.Review
import kotlinx.serialization.Serializable

@Serializable
data class ReviewsResponse(
    val data: List<Review>,
)
