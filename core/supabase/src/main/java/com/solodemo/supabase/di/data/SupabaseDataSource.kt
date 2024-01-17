package com.solodemo.supabase.di.data

import com.solo.components.state.RequestState
import com.solodemo.supabase.model.Cart
import com.solodemo.supabase.model.Menu
import com.solodemo.supabase.model.Review
import com.solodemo.supabase.model.UserDetails
import com.solodemo.supabase.util.toUserDetails
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SupabaseDataSource @Inject constructor(private val supaBaseClient: SupabaseClient) {

    fun supaBaseClient(): SupabaseClient {
        return supaBaseClient
    }

    fun insertCart(cart: Cart): Flow<RequestState<Unit>>  {
        return flow {
            emit(RequestState.Loading)
            try {
                supaBaseClient.postgrest["cart"].insert(cart)
                emit(RequestState.Success(Unit))

            } catch (e: Exception) {
                emit(RequestState.Error(e))
            }
        }
    }

    fun updateById(id: Int, cart: Cart): Flow<RequestState<Unit>> {
        return flow {
            emit(RequestState.Loading)
            try {
                supaBaseClient.postgrest["cart"].update(cart) {
                    filter { eq("id", id) }
                }
                emit(RequestState.Success(Unit))

            } catch (e: Exception) {
                emit(RequestState.Error(e))
            }
        }
    }

    fun deleteCartItemById(id: Int): Flow<RequestState<Unit>> {
        return flow {
            emit(RequestState.Loading)
            try {
                supaBaseClient.postgrest["cart"].delete {
                    filter { eq("id", id) }
                }
                emit(RequestState.Success(Unit))

            } catch (e: Exception) {
                emit(RequestState.Error(e))
            }
        }
    }

    fun deleteAllCartItem(): Flow<RequestState<Unit>> {
        return flow {
            emit(RequestState.Loading)
            try {
                supaBaseClient.postgrest["cart"].delete{
                    filter { neq("id",1) }
                }
                emit(RequestState.Success(Unit))
            } catch (e: Exception) {
                emit(RequestState.Error(e))
            }
        }
    }

    fun getCartList(): Flow<RequestState<List<Cart>>> {
        return flow {
            emit(RequestState.Loading)
            try {
                val result = supaBaseClient.postgrest["cart"].select()
                val carts = result.decodeList<Cart>()
                emit(RequestState.Success(carts))

            } catch (e: Exception) {
                emit(RequestState.Error(e))
            }
        }
    }
    fun getReviews(): Flow<RequestState<List<Review>>> {
        return flow {
            emit(RequestState.Loading)
            try {
                val result = supaBaseClient.postgrest["review"].select()
                val reviews = result.decodeList<Review>()
                emit(RequestState.Success(reviews))

            } catch (e: Exception) {
                emit(RequestState.Error(e))
            }
        }
    }

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

    fun signUpEmail(authEmail: String, authPassword: String): Flow<RequestState<Unit>> {
        return flow {
            emit(RequestState.Loading)
            try {
                supaBaseClient.auth.signUpWith(Email) {
                    email = authEmail
                    password = authPassword
                }
                emit(RequestState.Success(Unit))
            } catch (e: Exception) {
                emit(RequestState.Error(e))
            }
        }
    }

    fun authEmail(authEmail: String, authPassword: String): Flow<RequestState<Unit>> {
        return flow {
            emit(RequestState.Loading)
            try {
                supaBaseClient.auth.signInWith(Email) {
                    email = authEmail
                    password = authPassword
                }
                emit(RequestState.Success(Unit))
            } catch (e: Exception) {
                emit(RequestState.Error(e))
            }
        }
    }

    fun getCurrentAccessToken(): Flow<RequestState<String>> {
        return flow {
            emit(RequestState.Loading)
            try {
                val accessToken = supaBaseClient.auth.currentAccessTokenOrNull()
                if (accessToken != null) {
                    emit(RequestState.Success(accessToken))
                } else {
                    emit(RequestState.Error(Exception("Access token is null")))
                }
            } catch (e: Exception) {
                emit(RequestState.Error(e))
            }
        }
    }

    fun refreshAccessToken(token: String): Flow<RequestState<Unit>> {
        return flow {
            emit(RequestState.Loading)
            try {
                supaBaseClient.auth.retrieveUser(jwt = token)
                supaBaseClient.auth.refreshCurrentSession()
                emit(RequestState.Success(Unit))
            } catch (e: Exception) {
                emit(RequestState.Error(e))
            }
        }
    }

    fun getUserInfo(token: String): Flow<RequestState<UserDetails>> {
        return flow {
            emit(RequestState.Loading)
            try {
                val userData = supaBaseClient.auth.retrieveUser(jwt = token).userMetadata
                val userDetails: UserDetails? = userData?.toUserDetails()

                if (userDetails != null) {
                    emit(RequestState.Success(userDetails))
                }
            } catch (e: Exception) {
                emit(RequestState.Error(e))
            }
        }
    }

    fun signOut(): Flow<RequestState<Unit>> {
        return flow {
            emit(RequestState.Loading)
            try {
                supaBaseClient.auth.signOut()
                emit(RequestState.Success(Unit))
            } catch (e: Exception) {
                emit(RequestState.Error(e))
            }
        }
    }
}