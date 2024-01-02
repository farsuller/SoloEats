package com.solodemo.supabase.repository

import com.solodemo.supabase.data.MenusDataSource
import com.solodemo.supabase.model.Menu
import com.solodemo.supabase.model.RequestState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultMenusRepository @Inject constructor(
    private val menusDataSource: MenusDataSource
): MenusRepository {
    override fun getMenus(): Flow<RequestState<List<Menu>>> {
        return menusDataSource.getMenus()
    }
}