package com.solodemo.main.screens.account

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.state.RequestState
import com.solo.util.routes.ScreensRoutes
import com.solodemo.main.MainViewModel
import kotlinx.coroutines.flow.collectLatest

fun NavGraphBuilder.accountRoute(
    paddingValues: PaddingValues,
    viewModel: MainViewModel,
    navigateToAuth: () -> Unit
) {
    composable(route = ScreensRoutes.Account.route) {

        LaunchedEffect(key1 = Unit) {

            viewModel.uiState.collectLatest { data ->
                when (data) {
                    RequestState.Loading -> {}

                    is RequestState.Success -> {
                        navigateToAuth()
                    }
                    is RequestState.Error -> {}

                    else -> {}
                }
            }
        }

        AccountScreen(
            paddingValues = paddingValues,
            onButtonClicked = {
                viewModel.signOut()
            }
        )
    }
}