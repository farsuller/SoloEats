package com.solo.solodemo.presentations.main.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.solodemo.ScreensRoutes

fun NavGraphBuilder.homeRoute(paddingValues: PaddingValues) {
    composable(route = ScreensRoutes.Home.route) {

        HomeScreen(paddingValues = paddingValues)
    }
}