package com.solodemo.database.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductDetails(
    val name: String,
    val image: String,
    val price: String,
    val quantity: Int,
    val originalPrice: String,
) : Parcelable
