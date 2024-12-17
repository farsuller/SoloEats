package com.solodemo.main.presentations

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
        val menusState by viewModel.menusState.collectAsStateWithLifecycle()
        val reviewsState by viewModel.reviewsState.collectAsStateWithLifecycle()
        val cartState by viewModel.cartState.collectAsStateWithLifecycle()
        val loadData by viewModel.isLoadingData.collectAsStateWithLifecycle()
        val accountState by viewModel.accountState.collectAsStateWithLifecycle()
        val productState by viewModel.productsState.collectAsStateWithLifecycle()

        LaunchedEffect(key1 = viewModel) {
            onDataLoaded()
            viewModel.getCartList()
        }

        MainScreen(
            isLoadingData = loadData,
            menusState = menusState,
            reviewsState = reviewsState,
            productState = productState,
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
