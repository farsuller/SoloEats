package com.solodemo.supabase.di.data

import com.solo.components.state.RequestState
import com.solodemo.supabase.model.Menu

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.builtin.Email
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
                emit(RequestState.Error(e))
            }
        }
    }
    fun signUpEmail(authEmail:String, authPassword:String): Flow<RequestState<Unit>>{
        return flow {
            emit(RequestState.Loading)
            try {
                supaBaseClient.auth.signUpWith(Email){
                    email = authEmail
                    password = authPassword
                }
                emit(RequestState.Success(Unit))
            }catch (e:Exception){
                emit(RequestState.Error(e))
            }
        }
    }

    fun authEmail(authEmail:String, authPassword:String): Flow<RequestState<Unit>>{
        return flow {
            emit(RequestState.Loading)
            try {
                supaBaseClient.auth.signInWith(Email){
                    email = authEmail
                    password = authPassword
                }
                emit(RequestState.Success(Unit))
            }catch (e:Exception){
                emit(RequestState.Error(e))
            }
        }
    }
    fun getCurrentAccessToken(): Flow<RequestState<String>>{
        return flow {
            emit(RequestState.Loading)
            try{
                val accessToken = supaBaseClient.auth.currentAccessTokenOrNull()
                if (accessToken != null) {
                    emit(RequestState.Success(accessToken))
                } else {
                    emit(RequestState.Error(Exception("Access token is null")))
                }
            }catch (e: Exception){
                emit(RequestState.Error(e))
            }
        }
    }
    fun refreshAccessToken(token : String): Flow<RequestState<Unit>>{
        return flow {
            emit(RequestState.Loading)
            try {
                supaBaseClient.auth.retrieveUser(jwt = token)
                supaBaseClient.auth.refreshCurrentSession()
                emit(RequestState.Success(Unit))
            }catch (e: Exception){
                emit(RequestState.Error(e))
            }
        }
    }

    fun singOut(): Flow<RequestState<Unit>>{
        return flow {
            emit(RequestState.Loading)
            try {
                supaBaseClient.auth.signOut()
               emit(RequestState.Success(Unit))
            }catch (e:Exception){
                emit(RequestState.Error(e))
            }
        }
    }
}