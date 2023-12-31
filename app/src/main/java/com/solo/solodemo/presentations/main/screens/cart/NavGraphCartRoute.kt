package com.solo.solodemo.presentations.main.screens.cart

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.solodemo.ScreensRoutes

fun NavGraphBuilder.cartRoute() {
    composable(route = ScreensRoutes.Cart.route) {

        CartScreen(onButtonClicked = {})
    }
}