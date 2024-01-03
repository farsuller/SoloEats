package com.solodemo.supabase.di.data

import com.solo.components.state.RequestState
import com.solodemo.supabase.model.Menu

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SupabaseDataSource @Inject constructor(private val supaBaseClient: SupabaseClient) {

    fun getMenus(): Flow<RequestState<List<Menu>>> {
        return flow {
            emit(RequestState.Loading)
            try {
                val result = supaBaseClient.postgrest["menu"].select()
                val menus = result.decodeList<Menu>()
                emit(RequestState.Success(menus))

            } catch (e: Exception) {
                RequestState.Error(e)
            }
        }
    }
}