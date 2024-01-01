package com.solodemo.auth.forgot

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.util.routes.ScreensRoutes


fun NavGraphBuilder.forgotRoute(onButtonClicked: () -> Unit,) {
    composable(route = ScreensRoutes.Forgot.route) {

        ForgotScreen(onButtonClicked = { onButtonClicked() })
    }
}