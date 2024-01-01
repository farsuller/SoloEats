package com.solodemo.auth.presenations.signup

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.util.routes.ScreensRoutes

fun NavGraphBuilder.signUpRoute(onButtonClicked: () -> Unit,) {
    composable(route = ScreensRoutes.SignUp.route) {
        SignUpScreen(onButtonClicked = {
            onButtonClicked()
        })
    }
}