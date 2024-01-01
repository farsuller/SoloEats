package com.solo.solodemo.presentations.main.screens.account

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.solodemo.ScreensRoutes

fun NavGraphBuilder.accountRoute(paddingValues: PaddingValues) {
    composable(route = ScreensRoutes.Account.route) {

        AccountScreen(
            onButtonClicked = {}
        )
    }
}