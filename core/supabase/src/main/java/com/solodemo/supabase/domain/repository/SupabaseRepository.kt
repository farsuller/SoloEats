package com.solodemo.supabase.domain.repository

import com.solodemo.supabase.model.Menu
import com.solo.components.state.RequestState
import kotlinx.coroutines.flow.Flow


typealias Menus = RequestState<List<Menu>>

interface SupabaseRepository {
    fun getMenus(): Flow<Menus>
    fun signInEmail(authEmail: String, authPassword: String): Flow<RequestState<Unit>>
    fun getCurrentAccessToken(): Flow<RequestState<String>>
    fun refreshAccessToken(token : String) : Flow <RequestState<Unit>>
    fun singOut(): Flow<RequestState<Unit>>
}