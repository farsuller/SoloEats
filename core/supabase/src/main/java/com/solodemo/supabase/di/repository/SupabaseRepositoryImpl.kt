package com.solodemo.supabase.di.repository

import com.solodemo.supabase.di.data.SupabaseDataSource
import com.solodemo.supabase.domain.repository.SupabaseRepository
import com.solodemo.supabase.model.Menu
import com.solo.components.state.RequestState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SupabaseRepositoryImpl @Inject constructor(
    private val supabaseDataSource: SupabaseDataSource
): SupabaseRepository {
    override fun getMenus(): Flow<RequestState<List<Menu>>> {
        return supabaseDataSource.getMenus()
    }

    override fun signInEmail(authEmail: String, authPassword: String): Flow<RequestState<Unit>> {
        return supabaseDataSource.authEmail(authEmail = authEmail,authPassword = authPassword)
    }

    override fun signUpEmail(authEmail: String, authPassword: String): Flow<RequestState<Unit>> {
        return supabaseDataSource.signUpEmail(authEmail = authEmail,authPassword = authPassword)
    }

    override fun getCurrentAccessToken(): Flow<RequestState<String>> {
        return supabaseDataSource.getCurrentAccessToken()
    }

    override fun refreshAccessToken(token: String): Flow<RequestState<Unit>> {
        return supabaseDataSource.refreshAccessToken(token = token)
    }

    override fun singOut(): Flow<RequestState<Unit>> {
        return supabaseDataSource.singOut()
    }
}