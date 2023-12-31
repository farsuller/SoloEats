package com.solo.solodemo.presentations.main.screens.payment

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.solodemo.ScreensRoutes

fun NavGraphBuilder.paymentRoute() {
    composable(route = ScreensRoutes.Payment.route) {

        PaymentScreen(onButtonClicked = {})
    }
}