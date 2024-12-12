package com.solodemo.database.domain.usecase.cart

import com.solodemo.database.domain.model.Cart
import com.solodemo.database.domain.repository.EatsCartRepository

class UpsertCart(
    private val eatsCartRepository: EatsCartRepository,
) {
    suspend operator fun invoke(cart: Cart) {
        eatsCartRepository.upsertCart(cart = cart)
    }
}
