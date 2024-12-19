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
        return cartDao.getCartList()
    }

    override suspend fun upsertCart(cart: Cart) {
        return cartDao.upsert(cart = cart)
    }

    override suspend fun deleteCartItemById(id: Int) {
        return cartDao.deleteById(id = id)
    }

    override suspend fun deleteAllCart(): Int {
        return cartDao.deleteAllCartItem()
    }
}
