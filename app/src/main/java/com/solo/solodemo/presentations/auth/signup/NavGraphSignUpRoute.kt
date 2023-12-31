package com.solo.solodemo.presentations.auth.signup

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.solodemo.ScreensRoutes

fun NavGraphBuilder.signUpRoute(onButtonClicked: () -> Unit,) {
    composable(route = ScreensRoutes.SignUp.route) {
        SignUpScreen(onButtonClicked = {
            onButtonClicked()
        })
    }
}