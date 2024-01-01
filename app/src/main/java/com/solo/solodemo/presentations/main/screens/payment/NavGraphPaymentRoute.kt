package com.solo.solodemo.presentations.main.screens.payment

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.solodemo.ScreensRoutes

fun NavGraphBuilder.paymentRoute(paddingValues: PaddingValues) {
    composable(route = ScreensRoutes.Payment.route) {

        PaymentScreen(onButtonClicked = {})
    }
}