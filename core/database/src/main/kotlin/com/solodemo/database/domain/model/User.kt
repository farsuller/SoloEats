package com.solodemo.database.domain.model

import androidx.room.Entity

@Entity(tableName = "user")
data class User(
    val avatarUrl: String,
    val email: String,
    val emailVerified: Boolean,
    val fullName: String,
    val iss: String,
    val name: String,
    val phoneVerified: Boolean,
    val picture: String,
    val providerId: String,
    val sub: String,
)