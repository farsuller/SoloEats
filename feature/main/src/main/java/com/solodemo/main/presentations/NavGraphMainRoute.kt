package com.solodemo.main.presentations

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
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
        val reviews by viewModel.reviews
        val foodList = viewModel.getProductList(LocalContext.current)

        LaunchedEffect(key1 = viewModel) {
            onDataLoaded()
            viewModel.getReviews()
            viewModel.getMenus()
            viewModel.getUserInfo()
            viewModel.getCartList()
        }

        MainScreen(
            menus = menusList,
            reviews = reviews,
            foodList = foodList,
            viewModel = viewModel,
            navigateToAuth = navigateToAuth,
            navigateToProductList = navigateToProductList
        )
    }
}