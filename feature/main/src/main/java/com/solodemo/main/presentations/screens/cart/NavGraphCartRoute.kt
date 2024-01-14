package com.solodemo.main.presentations.screens.cart

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes
import com.solodemo.main.presentations.MainViewModel

fun NavGraphBuilder.cartRoute(paddingValues: PaddingValues, viewModel: MainViewModel) {
    composable(route = ScreensRoutes.Cart.route) {

        val carts by viewModel.carts
        val user by viewModel.user

        LaunchedEffect(key1 = true) {
            viewModel.getCartList()
        }
        CartScreen(paddingValues = paddingValues, carts = carts, users = user)
    }
}