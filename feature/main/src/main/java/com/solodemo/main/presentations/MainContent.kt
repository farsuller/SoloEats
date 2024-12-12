package com.solodemo.main.presentations

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.solo.components.routes.ScreensRoutes
import com.solodemo.database.domain.model.Cart
import com.solodemo.main.model.FoodCategory
import com.solodemo.main.presentations.dashboard.account.AccountState
import com.solodemo.main.presentations.dashboard.account.accountRoute
import com.solodemo.main.presentations.dashboard.cart.CartState
import com.solodemo.main.presentations.dashboard.cart.cartRoute
import com.solodemo.main.presentations.dashboard.home.homeRoute
import com.solodemo.main.presentations.dashboard.menu.menuRoute
import com.solodemo.main.presentations.dashboard.payment.paymentRoute
import com.solodemo.supabase.domain.repository.Menus
import com.solodemo.supabase.domain.repository.Reviews

@Composable
fun MainContent(
    paddingValues: PaddingValues,
    navController: NavHostController,
    menus: Menus,
    reviews: Reviews,
    cartState: CartState,
    accountState: AccountState,
    foodList: List<FoodCategory>,
    navigateToAuth: () -> Unit,
    homeLazyListState: LazyListState,
    navigateToProductList: (String) -> Unit,
    navigateToPlaceOrderSuccess: () -> Unit,
    insertCart: (Cart) -> Unit,
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
            navigateToProductList = navigateToProductList,
            insertCart = insertCart,
        )
        menuRoute(
            paddingValues = paddingValues,
            menus = menus,
            navigateToProductList = navigateToProductList,
        )
        paymentRoute(paddingValues = paddingValues)
        cartRoute(
            paddingValues = paddingValues,
            accountState = accountState,
            navigateToPlaceOrderSuccess = navigateToPlaceOrderSuccess,
        )
        accountRoute(
            paddingValues = paddingValues,
            accountState = accountState,
            navigateToAuth = navigateToAuth,
        )
    }
}
