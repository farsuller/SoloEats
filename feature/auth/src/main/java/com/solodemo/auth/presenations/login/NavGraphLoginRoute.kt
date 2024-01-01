package com.solodemo.auth.presenations.login

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.util.routes.ScreensRoutes


fun NavGraphBuilder.loginRoute(
    navigateToMain: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToForgot: () -> Unit,
    onDataLoaded: () -> Unit
) {
    composable(route = ScreensRoutes.Auth.route) {
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




