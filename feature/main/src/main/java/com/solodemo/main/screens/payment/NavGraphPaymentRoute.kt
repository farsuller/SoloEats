package com.solodemo.main.screens.payment

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.util.routes.ScreensRoutes

fun NavGraphBuilder.paymentRoute(paddingValues: PaddingValues) {
    composable(route = ScreensRoutes.Payment.route) {

        PaymentScreen(onWalletClicked = {}, paddingValues = paddingValues)
    }
}