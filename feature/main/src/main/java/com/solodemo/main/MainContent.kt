package com.solodemo.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.solo.util.routes.ScreensRoutes
import com.solodemo.main.screens.account.accountRoute
import com.solodemo.main.screens.cart.cartRoute
import com.solodemo.main.screens.home.homeRoute
import com.solodemo.main.screens.menu.menuRoute
import com.solodemo.main.screens.payment.paymentRoute


@Composable
fun MainContent(
    paddingValues: PaddingValues,
    navController: NavHostController
    ) {
    NavHost(navController = navController, startDestination = ScreensRoutes.Home.route){
        homeRoute(paddingValues = paddingValues)
        menuRoute(paddingValues = paddingValues)
        paymentRoute(paddingValues = paddingValues)
        cartRoute(paddingValues = paddingValues)
        accountRoute(paddingValues = paddingValues)
    }


}
