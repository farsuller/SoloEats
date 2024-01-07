package com.solodemo.supabase.di.repository

import com.solodemo.supabase.di.data.SupabaseDataSource
import com.solodemo.supabase.domain.repository.SupabaseRepository
import com.solodemo.supabase.model.Menu
import com.solo.components.state.RequestState
import com.solodemo.supabase.model.Reel
import io.github.jan.supabase.SupabaseClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SupabaseRepositoryImpl @Inject constructor(
    private val supabaseDataSource: SupabaseDataSource
): SupabaseRepository {
    override fun getReels(): Flow<RequestState<List<Reel>>> {
        return supabaseDataSource.getReels()
    }


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

    override fun signOut(): Flow<RequestState<Unit>> {
        return supabaseDataSource.signOut()
    }

    override fun supaBaseClient(): SupabaseClient {
        return supabaseDataSource.supaBaseClient()
    }
}