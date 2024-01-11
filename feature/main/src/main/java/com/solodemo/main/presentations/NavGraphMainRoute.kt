package com.solodemo.main.presentations

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes
import com.solo.components.state.RequestState

fun NavGraphBuilder.mainRoute(
    onDataLoaded: () -> Unit,
    navigateToAuth: () -> Unit,
    navigateToProductList: (String) -> Unit
) {
    composable(route = ScreensRoutes.Main.route) {

        val viewModel = hiltViewModel<MainViewModel>()
        val menusList by viewModel.menus
        val reelsList by viewModel.reels

        LaunchedEffect(key1 = menusList) {
            if (menusList !is RequestState.Loading) {
                onDataLoaded()
            }
        }

        MainScreen(
            reels = reelsList,
            menus = menusList,
            viewModel = viewModel,
            navigateToAuth = navigateToAuth,
            navigateToProductList = navigateToProductList
        )
    }
}