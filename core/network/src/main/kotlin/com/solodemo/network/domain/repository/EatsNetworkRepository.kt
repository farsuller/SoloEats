package com.solodemo.network.domain.repository

import com.solodemo.network.data.ApiResult
import com.solodemo.network.data.remote.dto.CouponsResponse
import com.solodemo.network.data.remote.dto.MenusResponse
import com.solodemo.network.data.remote.dto.ProductsResponse
import com.solodemo.network.data.remote.dto.ReviewsResponse
import kotlinx.coroutines.flow.Flow

interface EatsNetworkRepository {
    suspend fun fetchMenus(): Flow<ApiResult<MenusResponse>>

    suspend fun fetchReviews(): Flow<ApiResult<ReviewsResponse>>

    suspend fun fetchCoupons(): Flow<ApiResult<CouponsResponse>>

    suspend fun fetchProducts(): Flow<ApiResult<ProductsResponse>>
}
