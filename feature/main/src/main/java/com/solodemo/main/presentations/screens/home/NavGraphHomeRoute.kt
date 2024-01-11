package com.solodemo.main.presentations.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes
import com.solodemo.main.presentations.MainViewModel
import com.solodemo.supabase.domain.repository.Menus

fun NavGraphBuilder.homeRoute(
    paddingValues: PaddingValues,
    menus: Menus,
    homeLazyListState: LazyListState,
    navigateToProductList: (String) -> Unit,
    viewModel: MainViewModel,
) {
    composable(route = ScreensRoutes.Home.route) {

        val reviewsList by viewModel.reviews

        HomeScreen(
            paddingValues = paddingValues,
            menus = menus,
            reviews = reviewsList,
            homeLazyListState = homeLazyListState,
            navigateToProductList = navigateToProductList
        )
    }
}