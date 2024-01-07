package com.solodemo.main.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.util.routes.ScreensRoutes
import com.solodemo.supabase.domain.repository.Menus
import com.solodemo.supabase.domain.repository.Reels

fun NavGraphBuilder.homeRoute(paddingValues: PaddingValues, menus: Menus, reels: Reels) {
    composable(route = ScreensRoutes.Home.route) {

        HomeScreen(paddingValues = paddingValues, menus = menus, reels = reels)
    }
}