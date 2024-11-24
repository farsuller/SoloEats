package com.solodemo.database.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class Cart(

    @PrimaryKey
    val id: Int,
    val productName: String? = null,
    val productImage: String? = null,
    val productPrice: String? = null,
    val productQuantity: Int? = null,
    val productPriceOriginal: String? = null,
)