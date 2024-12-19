package com.solodemo.home.presentations.dashboard.menu

import com.solodemo.network.domain.model.Menu

data class MenusState(
    val menusList: List<Menu>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
