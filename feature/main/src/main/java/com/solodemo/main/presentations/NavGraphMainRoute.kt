package com.solodemo.main.presentations

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes
import com.solo.components.state.AuthState
import com.solodemo.main.presentations.dashboard.home.HomeEvent

fun NavGraphBuilder.mainRoute(
    onDataLoaded: (Boolean) -> Unit,
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
        val authState = viewModel.authState.collectAsStateWithLifecycle()

        LaunchedEffect(key1 = authState.value) {
            onDataLoaded(false)
            when (authState.value) {
                is AuthState.Unauthenticated -> navigateToAuth()
                else -> Unit
            }
        }

        MainScreen(
            isLoadingData = loadData,
            menusState = menusState,
            reviewsState = reviewsState,
            productState = productState,
            cartState = cartState,
            accountState = accountState,
            navigateToAuth = {
                navigateToAuth()
                viewModel.logOut()
            },
            navigateToProductList = navigateToProductList,
            navigateToPlaceOrderSuccess = navigateToPlaceOrderSuccess,
            insertCart = {
                viewModel.onEvent(HomeEvent.UpsertCartItem(it))
            },
        )
    }
}
