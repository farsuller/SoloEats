package com.solo.solodemo.presentations.auth.forgot

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.solodemo.ScreensRoutes

fun NavGraphBuilder.forgotRoute(onButtonClicked: () -> Unit,) {
    composable(route = ScreensRoutes.Forgot.route) {

        ForgotScreen(onButtonClicked = { onButtonClicked() })
    }
}