package com.solodemo.main.screens.cart

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.util.routes.ScreensRoutes

fun NavGraphBuilder.cartRoute(paddingValues: PaddingValues) {
    composable(route = ScreensRoutes.Cart.route) {

        CartScreen(onButtonClicked = {})
    }
}