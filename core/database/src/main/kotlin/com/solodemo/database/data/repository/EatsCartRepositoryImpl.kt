package com.solodemo.database.data.repository

import com.solodemo.database.data.local.CartDao
import com.solodemo.database.domain.model.Cart
import com.solodemo.database.domain.repository.EatsCartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EatsCartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao,
) :
    EatsCartRepository {
    override fun getCartList(): Flow<List<Cart>> {
        TODO("Not yet implemented")
    }

    override fun upsertCart(cart: Cart) {
        TODO("Not yet implemented")
    }

    override suspend fun selectCartById(id: Int): Cart? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCartItemById(id: Int) {
        TODO("Not yet implemented")
    }

    override fun deleteAllCartItem(): Flow<List<Cart>> {
        TODO("Not yet implemented")
    }
}
