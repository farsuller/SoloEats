package com.solodemo.database.domain.usecase.cart

import com.solodemo.database.domain.repository.EatsCartRepository

class DeleteCartItemById(
    private val eatsCartRepository: EatsCartRepository,
) {
    suspend operator fun invoke(deleteItemById: Int) {
        eatsCartRepository.deleteCartItemById(id = deleteItemById)
    }
}
