package com.solodemo.auth.presenations.login

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes

fun NavGraphBuilder.loginRoute(
    navigateToMain: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToForgot: () -> Unit,
    onDataLoaded: (Boolean) -> Unit,
) {
    composable(route = ScreensRoutes.Auth.route) {
        LaunchedEffect(key1 = Unit) {
            onDataLoaded(false)
        }

        LoginScreen(
            onForgotButtonClicked = { navigateToForgot() },
            onSignUpButtonClicked = { navigateToSignUp() },
            navigateToMain = navigateToMain,
        )
    }
}
