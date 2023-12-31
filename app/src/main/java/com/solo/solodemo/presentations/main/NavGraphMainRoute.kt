package com.solo.solodemo.presentations.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.solodemo.ScreensRoutes

fun NavGraphBuilder.mainRoute() {
    composable(route = ScreensRoutes.Main.route) {
        MainScreen()
    }
}