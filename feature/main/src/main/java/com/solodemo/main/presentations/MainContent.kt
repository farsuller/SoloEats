package com.solodemo.main.presentations

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.solo.components.routes.ScreensRoutes
import com.solodemo.database.domain.model.Cart
import com.solodemo.main.presentations.dashboard.account.AccountState
import com.solodemo.main.presentations.dashboard.account.accountRoute
import com.solodemo.main.presentations.dashboard.cart.cartRoute
import com.solodemo.main.presentations.dashboard.home.ReviewsState
import com.solodemo.main.presentations.dashboard.home.homeRoute
import com.solodemo.main.presentations.dashboard.menu.MenusState
import com.solodemo.main.presentations.dashboard.menu.menuRoute
import com.solodemo.main.presentations.dashboard.payment.paymentRoute
import com.solodemo.main.presentations.products.ProductsState

@Composable
fun MainContent(
    paddingValues: PaddingValues,
    navController: NavHostController,
    menusState: MenusState,
    reviewsState: ReviewsState,
    accountState: AccountState,
    productState: ProductsState,
    navigateToAuth: () -> Unit,
    homeLazyListState: LazyListState,
    navigateToProductList: (String) -> Unit,
    navigateToPlaceOrderSuccess: () -> Unit,
    insertCart: (Cart) -> Unit,
    onPullRefresh: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = ScreensRoutes.Home.route,
    ) {
        homeRoute(
            paddingValues = paddingValues,
            menusState = menusState,
            reviewsState = reviewsState,
            productState = productState,
            homeLazyListState = homeLazyListState,
            navigateToProductList = navigateToProductList,
            insertCart = insertCart,
            onPullRefresh = onPullRefresh,
        )
        menuRoute(
            paddingValues = paddingValues,
            menusState = menusState,
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
