package com.solodemo.auth.login

import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.util.routes.ScreensRoutes
import com.solodemo.auth.login.LoginScreen


fun NavGraphBuilder.loginRoute(
    navigateToMain: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToForgot: () -> Unit,
    onDataLoaded: () -> Unit
) {
    composable(route = ScreensRoutes.Auth.route) {
        private val viewModel by viewModels<MainViewModel>()
        LaunchedEffect(key1 = Unit) {
            onDataLoaded()
        }
        LoginScreen(
            onGoogleButtonClicked = { navigateToMain() },
            onForgotButtonClicked = { navigateToForgot() },
            onSignUpButtonClicked = { navigateToSignUp() }
        )
    }
}




