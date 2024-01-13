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
import com.solodemo.supabase.domain.repository.Users


@Composable
fun MainContent(
    paddingValues: PaddingValues,
    navController: NavHostController,
    menus: Menus,
    users: Users,
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
            homeLazyListState = homeLazyListState,
            viewModel = viewModel,
            navigateToProductList = navigateToProductList
        )
        menuRoute(
            paddingValues = paddingValues,
            menus = menus,
            navigateToProductList = navigateToProductList
        )
        paymentRoute(paddingValues = paddingValues)
        cartRoute(
            paddingValues = paddingValues,
            viewModel = viewModel
        )
        accountRoute(
            users = users,
            paddingValues = paddingValues,
            viewModel = viewModel,
            navigateToAuth = navigateToAuth
        )
    }


}

