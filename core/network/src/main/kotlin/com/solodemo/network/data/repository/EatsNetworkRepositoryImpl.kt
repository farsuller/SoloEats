package com.solodemo.network.data.repository

import com.solodemo.network.data.ApiResult
import com.solodemo.network.data.remote.EatsApi
import com.solodemo.network.data.remote.dto.CouponsResponse
import com.solodemo.network.data.remote.dto.MenusResponse
import com.solodemo.network.data.remote.dto.ProductsResponse
import com.solodemo.network.data.remote.dto.ReviewsResponse
import com.solodemo.network.data.safeApiCall
import com.solodemo.network.domain.repository.EatsNetworkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EatsNetworkRepositoryImpl @Inject constructor(
    private val api: EatsApi,
) : EatsNetworkRepository {

    override suspend fun fetchMenus(): Flow<ApiResult<MenusResponse>> = safeApiCall {
        api.getMenus()
    }

    override suspend fun fetchReviews(): Flow<ApiResult<ReviewsResponse>> = safeApiCall {
        api.getReviews()
    }

    override suspend fun fetchCoupons(): Flow<ApiResult<CouponsResponse>> = safeApiCall {
        api.getCoupons()
    }

    override suspend fun fetchProducts(): Flow<ApiResult<ProductsResponse>> = safeApiCall {
        api.getProducts()
    }
}
