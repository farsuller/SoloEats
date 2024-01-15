package com.solodemo.main.presentations.screens.account

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.Constants
import com.solo.components.routes.ScreensRoutes
import com.solo.components.state.RequestState
import com.solodemo.main.presentations.MainViewModel
import com.solodemo.supabase.domain.repository.Users
import kotlinx.coroutines.flow.collectLatest

fun NavGraphBuilder.accountRoute(
    paddingValues: PaddingValues,
    viewModel: MainViewModel,
    navigateToAuth: () -> Unit,
    users: Users
) {
    composable(route = ScreensRoutes.Account.route) {


        val uriHandler = LocalUriHandler.current

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
            users = users,
            paddingValues = paddingValues,
            onSignOutButtonClicked = {
                viewModel.signOut()
            },
            onPrivacyPolicyClicked = {
                uriHandler.openUri(Constants.PRIVACY_POLICY_LINK)
            },
        )
    }
}