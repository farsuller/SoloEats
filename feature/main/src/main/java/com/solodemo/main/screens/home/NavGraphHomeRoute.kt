package com.solodemo.main.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.solo.util.routes.ScreensRoutes
import com.solodemo.main.model.FoodCategory
import com.solodemo.supabase.domain.repository.Menus
import com.solodemo.supabase.domain.repository.Reels

fun NavGraphBuilder.homeRoute(
    paddingValues: PaddingValues,
    menus: Menus,
    reels: Reels,
    homeLazyListState: LazyListState,
    navController: NavHostController,
) {
    composable(route = ScreensRoutes.Home.route) {

        HomeScreen(
            paddingValues = paddingValues,
            menus = menus,
            reels = reels,
            homeLazyListState = homeLazyListState,
            navController = navController
        )
    }
}