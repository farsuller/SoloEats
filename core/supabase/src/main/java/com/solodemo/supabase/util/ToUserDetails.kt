package com.solodemo.supabase.util

import com.solodemo.supabase.model.UserDetails
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

fun JsonObject?.toUserDetails(): UserDetails? {
    return try {
        this?.let { Json.decodeFromJsonElement(UserDetails.serializer(), it) }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
