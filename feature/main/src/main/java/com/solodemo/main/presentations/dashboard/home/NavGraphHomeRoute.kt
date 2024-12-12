package com.solodemo.main.presentations.dashboard.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes
import com.solodemo.database.domain.model.Cart
import com.solodemo.main.model.FoodCategory
import com.solodemo.supabase.domain.repository.Menus
import com.solodemo.supabase.domain.repository.Reviews

fun NavGraphBuilder.homeRoute(
    paddingValues: PaddingValues,
    menus: Menus,
    reviews: Reviews,
    foodList: List<FoodCategory>,
    homeLazyListState: LazyListState,
    navigateToProductList: (String) -> Unit,
    insertCart: (Cart) -> Unit,
) {
    composable(route = ScreensRoutes.Home.route) {
        val context = LocalContext.current

        HomeScreen(
            paddingValues = paddingValues,
            menus = menus,
            reviews = reviews,
            foodList = foodList,
            homeLazyListState = homeLazyListState,
            navigateToProductList = navigateToProductList,
            popularAddToCartClicked = { cart ->
                insertCart(cart)
            },
        )
    }
}
