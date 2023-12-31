package com.solo.solodemo.presentations.main.screens.account

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.solodemo.ScreensRoutes

fun NavGraphBuilder.accountRoute() {
    composable(route = ScreensRoutes.Account.route) {

        AccountScreen(
            onButtonClicked = {}
        )
    }
}