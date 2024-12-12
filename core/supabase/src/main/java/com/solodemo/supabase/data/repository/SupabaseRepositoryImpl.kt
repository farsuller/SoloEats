package com.solodemo.supabase.data.repository

import com.solo.components.state.RequestState
import com.solodemo.supabase.data.remote.SupabaseDataSource
import com.solodemo.supabase.domain.model.Menu
import com.solodemo.supabase.domain.model.Review
import com.solodemo.supabase.domain.repository.SupabaseRepository
import com.solodemo.supabase.model.UserDetails
import io.github.jan.supabase.SupabaseClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SupabaseRepositoryImpl @Inject constructor(
    private val supabaseDataSource: SupabaseDataSource,
) : SupabaseRepository {
//    override fun deleteAllCartItem(): Flow<RequestState<Unit>> {
//        return supabaseDataSource.deleteAllCartItem()
//    }
//
//    override fun updateCartItem(id: Int, cart: Cart): Flow<RequestState<Unit>> {
//        return supabaseDataSource.updateById(id = id, cart = cart)
//    }
//
//    override fun deleteCartItem(id: Int): Flow<RequestState<Unit>> {
//        return supabaseDataSource.deleteCartItemById(id = id)
//    }
//
//    override fun insertCart(cart: Cart): Flow<RequestState<Unit>> {
//        return supabaseDataSource.insertCart(cart = cart)
//    }
//
//    override fun getCartList(): Flow<RequestState<List<Cart>>> {
//        return supabaseDataSource.getCartList()
//    }

    override fun getReviews(): Flow<RequestState<List<Review>>> {
        return supabaseDataSource.getReviews()
    }

    override fun getMenus(): Flow<RequestState<List<Menu>>> {
        return supabaseDataSource.getMenus()
    }

    override fun signInEmail(authEmail: String, authPassword: String): Flow<RequestState<Unit>> {
        return supabaseDataSource.authEmail(authEmail = authEmail, authPassword = authPassword)
    }

    override fun signUpEmail(authEmail: String, authPassword: String): Flow<RequestState<Unit>> {
        return supabaseDataSource.signUpEmail(authEmail = authEmail, authPassword = authPassword)
    }

    override fun getCurrentAccessToken(): Flow<RequestState<String>> {
        return supabaseDataSource.getCurrentAccessToken()
    }

    override fun refreshAccessToken(token: String): Flow<RequestState<Unit>> {
        return supabaseDataSource.refreshAccessToken(token = token)
    }

    override fun getUserInfo(token: String): Flow<RequestState<UserDetails>> {
        return supabaseDataSource.getUserInfo(token = token)
    }

    override fun signOut(): Flow<RequestState<Unit>> {
        return supabaseDataSource.signOut()
    }

    override fun supaBaseClient(): SupabaseClient {
        return supabaseDataSource.supaBaseClient()
    }
}
