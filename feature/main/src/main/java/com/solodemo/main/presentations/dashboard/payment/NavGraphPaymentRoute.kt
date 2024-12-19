package com.solodemo.main.presentations.dashboard.payment

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes

fun NavGraphBuilder.paymentRoute(paddingValues: PaddingValues) {
    composable(route = ScreensRoutes.Payment.route) {
        PaymentScreen(paddingValues = paddingValues)
    }
}
