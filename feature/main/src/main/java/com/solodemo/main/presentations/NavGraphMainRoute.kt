package com.solodemo.main.presentations

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes

fun NavGraphBuilder.mainRoute(
    onDataLoaded: () -> Unit,
    navigateToAuth: () -> Unit,
    navigateToProductList: (String) -> Unit
) {
    composable(route = ScreensRoutes.Main.route) {

        val viewModel = hiltViewModel<MainViewModel>()
        val menusList by viewModel.menus
        val user by viewModel.user

        LaunchedEffect(key1 = true) {
            onDataLoaded()
        }

        MainScreen(
            menus = menusList,
            users = user,
            viewModel = viewModel,
            navigateToAuth = navigateToAuth,
            navigateToProductList = navigateToProductList
        )
    }
}