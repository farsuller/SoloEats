package com.solodemo.main.presentations.placeorder

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes

fun NavGraphBuilder.placeOrderRoute(onNavigateToMain: () -> Unit) {
    composable(route = ScreensRoutes.PlaceOrder.route,
        enterTransition = {
            // Custom enter transition (push up)
            slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(400)
            ) + fadeIn(animationSpec = tween(400))
        },
        exitTransition = {
            // Custom exit transition (slide down)
            slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(400)
            ) + fadeOut(animationSpec = tween(400))
        }) {

        PlaceOrderScreen(onNavigateToMain = onNavigateToMain)

    }
}




