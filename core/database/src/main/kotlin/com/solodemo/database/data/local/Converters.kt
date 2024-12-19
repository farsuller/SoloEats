package com.solodemo.database.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.solodemo.database.domain.model.ProductDetails

class Converters {

    @TypeConverter
    fun fromProductDetails(productDetails: ProductDetails?): String? {
        return Gson().toJson(productDetails)
    }

    @TypeConverter
    fun toProductDetails(productDetailsString: String?): ProductDetails? {
        return if (productDetailsString.isNullOrEmpty()) {
            null
        } else {
            Gson().fromJson(productDetailsString, object : TypeToken<ProductDetails>() {}.type)
        }
    }
}
