package com.solodemo.auth.presenations.signup

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes
import com.solodemo.auth.presenations.AuthViewModel

fun NavGraphBuilder.signUpRoute(onBackPressClicked: () -> Unit) {
    composable(route = ScreensRoutes.SignUp.route) {
        val authViewModel = hiltViewModel<AuthViewModel>()

        SignUpScreen(
            onBackPressClicked = onBackPressClicked,
            onSignUpButtonClicked = { email, password ->
                onBackPressClicked()
                authViewModel.signUp(email, password)
            },
        )
    }
}
