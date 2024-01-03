package com.solodemo.supabase.di.repository

import com.solodemo.supabase.di.data.SupabaseDataSource
import com.solodemo.supabase.domain.repository.MenusRepository
import com.solodemo.supabase.model.Menu
import com.solo.components.state.RequestState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MenusRepositoryImpl @Inject constructor(
    private val supabaseDataSource: SupabaseDataSource
): MenusRepository {
    override fun getMenus(): Flow<com.solo.components.state.RequestState<List<Menu>>> {
        return supabaseDataSource.getMenus()
    }
}