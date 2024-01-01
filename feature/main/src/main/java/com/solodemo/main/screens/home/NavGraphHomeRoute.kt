package com.solodemo.main.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.util.routes.ScreensRoutes

fun NavGraphBuilder.homeRoute(paddingValues: PaddingValues) {
    composable(route = ScreensRoutes.Home.route) {

        HomeScreen(paddingValues = paddingValues)
    }
}