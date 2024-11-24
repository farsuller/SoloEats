package com.solodemo.main.presentations.dashboard.home

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes
import com.solodemo.main.model.FoodCategory
import com.solodemo.main.presentations.MainViewModel
import com.solodemo.supabase.domain.repository.Menus
import com.solodemo.supabase.domain.repository.Reviews
import com.solodemo.supabase.domain.model.Cart

fun NavGraphBuilder.homeRoute(
    paddingValues: PaddingValues,
    menus: Menus,
    reviews: Reviews,
    foodList: List<FoodCategory>,
    homeLazyListState: LazyListState,
    navigateToProductList: (String) -> Unit,
    viewModel: MainViewModel,
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
            popularAddToCartClicked = { cart: Cart ->
                viewModel.insertCart(
                    cart = cart,
                    onSuccess = {
                        Toast.makeText(
                            context,
                            "Success! Your item has been added to the cart",
                            Toast.LENGTH_SHORT,
                        ).show()
                    },
                    onError = {
                        Toast.makeText(
                            context,
                            "Failed! Your item has been existing to the cart",
                            Toast.LENGTH_SHORT,
                        ).show()
                    },
                )
            },
        )
    }
}
