package com.solodemo.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.util.routes.ScreensRoutes

fun NavGraphBuilder.mainRoute() {
    composable(route = ScreensRoutes.Main.route) {
        MainScreen()
    }
}