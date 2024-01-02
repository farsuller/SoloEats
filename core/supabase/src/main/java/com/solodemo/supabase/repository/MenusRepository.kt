package com.solodemo.supabase.repository

import com.solodemo.supabase.model.Menu
import com.solodemo.supabase.model.RequestState
import kotlinx.coroutines.flow.Flow



typealias Menus = RequestState<List<Menu>>

interface MenusRepository {
    fun getMenus(): Flow<Menus>
}