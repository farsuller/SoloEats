package com.solodemo.database.domain.usecase.cart

import com.solodemo.database.domain.model.Cart
import com.solodemo.database.domain.repository.EatsCartRepository
import kotlinx.coroutines.flow.Flow

class GetCartList(
    private val eatsCartRepository: EatsCartRepository,
) {
    operator fun invoke(): Flow<List<Cart>> {
        return eatsCartRepository.getCartList()
    }
}
