package com.solo.solodemo.presentations.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.solo.solodemo.ScreensRoutes
import com.solo.solodemo.presentations.main.screens.account.accountRoute
import com.solo.solodemo.presentations.main.screens.cart.cartRoute
import com.solo.solodemo.presentations.main.screens.home.homeRoute
import com.solo.solodemo.presentations.main.screens.menu.menuRoute
import com.solo.solodemo.presentations.main.screens.payment.paymentRoute


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

