package com.solodemo.main.presentations.dashboard.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes
import com.solodemo.database.domain.model.Cart
import com.solodemo.main.presentations.dashboard.menu.MenusState
import com.solodemo.main.presentations.products.ProductsState

fun NavGraphBuilder.homeRoute(
    paddingValues: PaddingValues,
    menusState: MenusState,
    reviewsState: ReviewsState,
    productState: ProductsState,
    homeLazyListState: LazyListState,
    navigateToProductList: (String) -> Unit,
    insertCart: (Cart) -> Unit,
) {
    composable(route = ScreensRoutes.Home.route) {
        HomeScreen(
            paddingValues = paddingValues,
            menusState = menusState,
            reviewsState = reviewsState,
            productState = productState,
            homeLazyListState = homeLazyListState,
            navigateToProductList = navigateToProductList,
            popularAddToCartClicked = { cart ->
                insertCart(cart)
            },
        )
    }
}
