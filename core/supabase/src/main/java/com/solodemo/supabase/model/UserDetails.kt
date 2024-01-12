package com.solodemo.supabase.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDetails(
    @SerialName("avatar_url") val avatarUrl: String,
    val email: String,
    @SerialName("email_verified") val emailVerified: Boolean,
    @SerialName("full_name") val fullName: String,
    val iss: String,
    val name: String,
    @SerialName("phone_verified") val phoneVerified: Boolean,
    val picture: String,
    @SerialName("provider_id") val providerId: String,
    val sub: String
)