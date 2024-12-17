package com.solodemo.network.data.remote

import com.solodemo.network.data.remote.dto.CouponsResponse
import com.solodemo.network.data.remote.dto.MenusResponse
import com.solodemo.network.data.remote.dto.ProductsResponse
import com.solodemo.network.data.remote.dto.ReviewsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class EatsApi @Inject constructor(
    private val client: HttpClient,
) {
    suspend fun getMenus(): MenusResponse {
        return client.get("api/menus").body()
    }

    suspend fun getReviews(): ReviewsResponse {
        return client.get("api/reviews").body()
    }

    suspend fun getCoupons(): CouponsResponse {
        return client.get("api/coupons").body()
    }

    suspend fun getProducts(): ProductsResponse {
        return client.get("api/products").body()
    }
}
