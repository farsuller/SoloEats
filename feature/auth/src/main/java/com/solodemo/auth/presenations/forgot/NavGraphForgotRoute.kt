package com.solodemo.auth.presenations.forgot

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes

fun NavGraphBuilder.forgotRoute(onButtonClicked: () -> Unit) {
    composable(route = ScreensRoutes.Forgot.route) {
        ForgotScreen(onButtonClicked = { onButtonClicked() })
    }
}
