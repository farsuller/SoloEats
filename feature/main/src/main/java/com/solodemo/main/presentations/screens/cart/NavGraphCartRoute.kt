package com.solodemo.main.presentations.screens.cart

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes

fun NavGraphBuilder.cartRoute(paddingValues: PaddingValues) {
    composable(route = ScreensRoutes.Cart.route) {


        val viewModel = hiltViewModel<CartViewModel>()
        val carts by viewModel.cartList

        LaunchedEffect(key1 = carts) {
            viewModel.getCartList()
            viewModel.getUserInfo()
        }

        CartScreen(paddingValues = paddingValues, carts = carts, cartViewModel = viewModel)
    }
}