package com.solodemo.main.presentations

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.solo.components.routes.ScreensRoutes
import com.solodemo.main.model.FoodCategory
import com.solodemo.main.presentations.dashboard.account.accountRoute
import com.solodemo.main.presentations.dashboard.cart.cartRoute
import com.solodemo.main.presentations.dashboard.home.homeRoute
import com.solodemo.main.presentations.dashboard.menu.menuRoute
import com.solodemo.main.presentations.dashboard.payment.paymentRoute
import com.solodemo.supabase.domain.repository.Carts
import com.solodemo.supabase.domain.repository.Menus
import com.solodemo.supabase.domain.repository.Reviews

@Composable
fun MainContent(
    paddingValues: PaddingValues,
    navController: NavHostController,
    menus: Menus,
    reviews: Reviews,
    carts: Carts,
    foodList: List<FoodCategory>,
    viewModel: MainViewModel,
    navigateToAuth: () -> Unit,
    homeLazyListState: LazyListState,
    navigateToProductList: (String) -> Unit,
    navigateToPlaceOrderSuccess: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = ScreensRoutes.Home.route,
    ) {
        homeRoute(
            paddingValues = paddingValues,
            menus = menus,
            reviews = reviews,
            foodList = foodList,
            homeLazyListState = homeLazyListState,
            viewModel = viewModel,
            navigateToProductList = navigateToProductList,
        )
        menuRoute(
            paddingValues = paddingValues,
            menus = menus,
            navigateToProductList = navigateToProductList,
        )
        paymentRoute(paddingValues = paddingValues)
        cartRoute(
            paddingValues = paddingValues,
            carts = carts,
            viewModel = viewModel,
            navigateToPlaceOrderSuccess = navigateToPlaceOrderSuccess,
        )
        accountRoute(
            paddingValues = paddingValues,
            viewModel = viewModel,
            navigateToAuth = navigateToAuth,
        )
    }
}
