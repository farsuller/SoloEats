package com.solodemo.database.domain.repository

import com.solodemo.database.domain.model.Cart
import kotlinx.coroutines.flow.Flow

interface EatsCartRepository {

    fun getCartList(): Flow<List<Cart>>

    fun upsertCart(cart: Cart)

    suspend fun selectCartById(id: Int): Cart?

    suspend fun deleteCartItemById(id: Int)

    fun deleteAllCartItem(): Flow<List<Cart>>
}
