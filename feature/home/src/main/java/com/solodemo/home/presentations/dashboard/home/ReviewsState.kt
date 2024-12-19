package com.solodemo.home.presentations.dashboard.home

import com.solodemo.network.domain.model.Review

data class ReviewsState(
    val reviewsList: List<Review>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
