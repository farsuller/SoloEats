package com.solodemo.network.domain.usecase

import com.solodemo.network.data.ApiResult
import com.solodemo.network.data.remote.dto.CouponsResponse
import com.solodemo.network.domain.repository.EatsNetworkRepository
import kotlinx.coroutines.flow.Flow

class GetCoupons(
    private val eatsCartRepository: EatsNetworkRepository,
) {
    suspend operator fun invoke(): Flow<ApiResult<CouponsResponse>> {
        return eatsCartRepository.fetchCoupons()
    }
}
