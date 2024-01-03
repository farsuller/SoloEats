package com.solodemo.supabase.domain.repository

import com.solodemo.supabase.model.Menu
import com.solo.components.state.RequestState
import kotlinx.coroutines.flow.Flow


typealias Menus = com.solo.components.state.RequestState<List<Menu>>

interface MenusRepository {
    fun getMenus(): Flow<Menus>
}