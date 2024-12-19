package com.solodemo.network.domain.usecase

import com.solodemo.network.data.ApiResult
import com.solodemo.network.data.remote.dto.MenusResponse
import com.solodemo.network.domain.repository.EatsNetworkRepository
import kotlinx.coroutines.flow.Flow

class GetMenus(
    private val eatsCartRepository: EatsNetworkRepository,
) {
    suspend operator fun invoke(): Flow<ApiResult<MenusResponse>> {
        return eatsCartRepository.fetchMenus()
    }
}
