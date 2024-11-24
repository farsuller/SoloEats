package com.solodemo.database.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "menu")
data class Menu(

    @PrimaryKey
    val id: Int,

    val menuName: String? = null,

    val menuImage: String? = null,

    val isAvailable: Boolean,
)