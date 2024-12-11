package com.solodemo.database.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "cart")
data class Cart(
    @PrimaryKey
    val id: Int,
    val productDetails: ProductDetails? = null,
) : Parcelable
