package com.solodemo.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.solo.util.routes.ScreensRoutes
import com.solodemo.main.model.FoodCategory
import com.solodemo.main.screens.account.accountRoute
import com.solodemo.main.screens.cart.cartRoute
import com.solodemo.main.screens.home.homeRoute
import com.solodemo.main.screens.menu.menuRoute
import com.solodemo.main.screens.payment.paymentRoute
import com.solodemo.main.screens.product.productSelectionRoute
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
    foodList : List<FoodCategory>,
) {
    NavHost(navController = navController,
        startDestination = ScreensRoutes.Home.route,
        ) {
        homeRoute(
            paddingValues = paddingValues,
            menus = menus,
            reels = reels,
            homeLazyListState = homeLazyListState,
            navController = navController
        )
        menuRoute(
            paddingValues = paddingValues,
            menus = menus
        )
        paymentRoute(paddingValues = paddingValues)
        cartRoute(paddingValues = paddingValues)
        accountRoute(
            paddingValues = paddingValues,
            viewModel = viewModel,
            navigateToAuth = navigateToAuth
        )
        productSelectionRoute(paddingValues = paddingValues,
            foodList = foodList)
    }


}

