package com.solodemo.main.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.util.routes.ScreensRoutes
import com.solodemo.supabase.repository.Menus

fun NavGraphBuilder.homeRoute(paddingValues: PaddingValues, menus: Menus) {
    composable(route = ScreensRoutes.Home.route) {

        HomeScreen(paddingValues = paddingValues, menus = menus)
    }
}