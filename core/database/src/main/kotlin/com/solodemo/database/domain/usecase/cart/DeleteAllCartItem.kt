package com.solodemo.database.domain.usecase.cart

import com.solodemo.database.domain.repository.EatsCartRepository

class DeleteAllCartItem(
    private val eatsCartRepository: EatsCartRepository,
) {
    suspend operator fun invoke(): Int {
        return eatsCartRepository.deleteAllCart()
    }
}
