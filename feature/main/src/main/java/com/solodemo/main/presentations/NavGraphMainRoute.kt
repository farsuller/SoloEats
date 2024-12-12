package com.solodemo.main.presentations

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes
import com.solodemo.main.presentations.dashboard.home.HomeEvent

fun NavGraphBuilder.mainRoute(
    onDataLoaded: () -> Unit,
    navigateToAuth: () -> Unit,
    navigateToProductList: (String) -> Unit,
    navigateToPlaceOrderSuccess: () -> Unit,
) {
    composable(route = ScreensRoutes.Main.route) {
        val viewModel = hiltViewModel<MainViewModel>()
        val menusList by viewModel.menus
        val reviews by viewModel.reviews
        val cartState by viewModel.cartState.collectAsStateWithLifecycle()
        val accountState by viewModel.accountState.collectAsStateWithLifecycle()
        val foodList = viewModel.getProductList(LocalContext.current)

        LaunchedEffect(key1 = viewModel) {
            onDataLoaded()
//            viewModel.getReviews()
//            viewModel.getMenus()
//            viewModel.getUserInfo()
            viewModel.getCartList()
        }

        MainScreen(
            menus = menusList,
            reviews = reviews,
            foodList = foodList,
            cartState = cartState,
            accountState = accountState,
            navigateToAuth = navigateToAuth,
            navigateToProductList = navigateToProductList,
            navigateToPlaceOrderSuccess = navigateToPlaceOrderSuccess,
            insertCart = {
                viewModel.onEvent(HomeEvent.UpsertCartItem(it))
            },
        )
    }
}
