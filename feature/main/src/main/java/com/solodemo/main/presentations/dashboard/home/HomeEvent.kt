package com.solodemo.main.presentations.dashboard.home

import com.solodemo.database.domain.model.Cart

sealed class HomeEvent {
    data class UpsertCartItem(val cartItem: Cart) : HomeEvent()
}