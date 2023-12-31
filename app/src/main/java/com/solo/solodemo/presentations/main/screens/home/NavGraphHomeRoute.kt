package com.solo.solodemo.presentations.main.screens.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.solodemo.ScreensRoutes

fun NavGraphBuilder.homeRoute() {
    composable(route = ScreensRoutes.Home.route) {

        HomeScreen(
            onButtonClicked = {}
        )
    }
}