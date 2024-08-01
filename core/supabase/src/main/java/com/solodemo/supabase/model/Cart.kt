package com.solodemo.supabase.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cart(
    val id: Int,
    @SerialName("product_name") val productName: String? = null,
    @SerialName("product_image") val productImage: String? = null,
    @SerialName("product_price") val productPrice: String? = null,
    @SerialName("product_quantity") val productQuantity: Int? = null,
    @SerialName("product_price_original") val productPriceOriginal: String? = null,
)
