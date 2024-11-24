package com.solodemo.database.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "review")
data class Review(

    @PrimaryKey
    val id: Int,
    val name: String? = null,
    val profileImage: String? = null,
    val reviewText: String? = null,
    val reviewStar: Int,
)