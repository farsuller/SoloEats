package com.solodemo.network.domain.usecase

import com.solodemo.network.data.ApiResult
import com.solodemo.network.data.remote.dto.ReviewsResponse
import com.solodemo.network.domain.repository.EatsNetworkRepository
import kotlinx.coroutines.flow.Flow

class GetReviews(
    private val eatsCartRepository: EatsNetworkRepository,
) {
    suspend operator fun invoke(): Flow<ApiResult<ReviewsResponse>> {
        return eatsCartRepository.fetchReviews()
    }
}
