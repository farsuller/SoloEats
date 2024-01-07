package com.solodemo.supabase.domain.repository

import com.solodemo.supabase.model.Menu
import com.solo.components.state.RequestState
import io.github.jan.supabase.SupabaseClient
import kotlinx.coroutines.flow.Flow


typealias Menus = RequestState<List<Menu>>

interface SupabaseRepository {
    fun getMenus(): Flow<Menus>
    fun signInEmail(authEmail: String, authPassword: String): Flow<RequestState<Unit>>
    fun signUpEmail(authEmail: String, authPassword: String): Flow<RequestState<Unit>>
    fun getCurrentAccessToken(): Flow<RequestState<String>>
    fun refreshAccessToken(token : String) : Flow <RequestState<Unit>>
    fun signOut(): Flow<RequestState<Unit>>
    fun supaBaseClient(): SupabaseClient
}