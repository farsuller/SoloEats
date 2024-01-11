package com.solodemo.main.presentations

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.solo.components.routes.ScreensRoutes
import com.solodemo.main.presentations.screens.account.accountRoute
import com.solodemo.main.presentations.screens.cart.cartRoute
import com.solodemo.main.presentations.screens.home.homeRoute
import com.solodemo.main.presentations.screens.menu.menuRoute
import com.solodemo.main.presentations.screens.payment.paymentRoute
import com.solodemo.supabase.domain.repository.Menus
import com.solodemo.supabase.domain.repository.Reels


@Composable
fun MainContent(
    paddingValues: PaddingValues,
    navController: NavHostController,
    menus: Menus,
    reels: Reels,
    viewModel: MainViewModel,
    navigateToAuth: () -> Unit,
    homeLazyListState: LazyListState,
    navigateToProductList: (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = ScreensRoutes.Home.route,
    ) {
        homeRoute(
            paddingValues = paddingValues,
            menus = menus,
            reels = reels,
            homeLazyListState = homeLazyListState,
            navigateToProductList = navigateToProductList
        )
        menuRoute(
            paddingValues = paddingValues,
            menus = menus,
            navigateToProductList = navigateToProductList
        )
        paymentRoute(paddingValues = paddingValues)
        cartRoute(paddingValues = paddingValues)
        accountRoute(
            paddingValues = paddingValues,
            viewModel = viewModel,
            navigateToAuth = navigateToAuth
        )
    }


}

