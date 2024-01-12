package com.solodemo.supabase.domain.repository

import com.solo.components.state.RequestState
import com.solodemo.supabase.model.Menu
import com.solodemo.supabase.model.Review
import com.solodemo.supabase.model.UserDetails
import io.github.jan.supabase.SupabaseClient
import kotlinx.coroutines.flow.Flow


typealias Menus = RequestState<List<Menu>>
typealias Reviews = RequestState<List<Review>>
typealias Users = RequestState<UserDetails>

interface SupabaseRepository {

    fun getReviews(): Flow<Reviews>
    fun getMenus(): Flow<Menus>
    fun signInEmail(authEmail: String, authPassword: String): Flow<RequestState<Unit>>
    fun signUpEmail(authEmail: String, authPassword: String): Flow<RequestState<Unit>>
    fun getCurrentAccessToken(): Flow<RequestState<String>>
    fun refreshAccessToken(token: String): Flow<RequestState<Unit>>
    fun getUserInfo(token: String): Flow<Users>
    fun signOut(): Flow<RequestState<Unit>>
    fun supaBaseClient(): SupabaseClient
}