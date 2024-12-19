package com.solodemo.database.domain.repository

import com.solodemo.database.domain.model.Cart
import kotlinx.coroutines.flow.Flow

interface EatsCartRepository {

    fun getCartList(): Flow<List<Cart>>

    suspend fun upsertCart(cart: Cart)

    suspend fun deleteCartItemById(id: Int)

    suspend fun deleteAllCart(): Int
}
