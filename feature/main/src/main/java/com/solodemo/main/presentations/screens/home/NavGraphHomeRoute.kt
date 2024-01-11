package com.solodemo.main.presentations.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes
import com.solodemo.supabase.domain.repository.Menus
import com.solodemo.supabase.domain.repository.Reels

fun NavGraphBuilder.homeRoute(
    paddingValues: PaddingValues,
    menus: Menus,
    reels: Reels,
    homeLazyListState: LazyListState,
    navigateToProductList: (String) -> Unit
) {
    composable(route = ScreensRoutes.Home.route) {

        HomeScreen(
            paddingValues = paddingValues,
            menus = menus,
            reels = reels,
            homeLazyListState = homeLazyListState,
            navigateToProductList = navigateToProductList
        )
    }
}