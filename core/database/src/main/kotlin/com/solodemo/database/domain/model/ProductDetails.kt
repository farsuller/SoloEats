package com.solodemo.database.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductDetails(
    val name: String? = null,
    val image: String? = null,
    val price: String? = null,
    val quantity: Int? = null,
    val originalPrice: String? = null,
) : Parcelable
