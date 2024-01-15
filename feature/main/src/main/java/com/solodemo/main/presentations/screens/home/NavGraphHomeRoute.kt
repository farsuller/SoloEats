package com.solodemo.main.presentations.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes
import com.solo.components.state.RequestState
import com.solodemo.main.model.FoodCategory
import com.solodemo.main.presentations.MainViewModel
import com.solodemo.supabase.domain.repository.Menus
import com.solodemo.supabase.domain.repository.Reviews
import com.solodemo.supabase.model.Cart
import kotlinx.coroutines.flow.collectLatest

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


        LaunchedEffect(key1 = viewModel.cartState) {
            viewModel.cartState.collectLatest { data ->
                when (data) {
                    RequestState.Loading -> {}
                    is RequestState.Success -> {

                        if (viewModel.isAddToCartClicked.value) {
                            Toast.makeText(
                                context, "Success! Your item has been added to the cart",
                                Toast.LENGTH_SHORT
                            ).show()
                            viewModel.getCartList()
                            viewModel.setAddToCartClicked(false)
                        }

                    }

                    is RequestState.Error -> {
                        if (viewModel.isAddToCartClicked.value) {
                            Toast.makeText(
                                context, "Failed! Your item has been existing to the cart",
                                Toast.LENGTH_SHORT
                            ).show()
                            viewModel.setAddToCartClicked(false)
                        }

                    }

                    else -> {}
                }
            }
        }
        HomeScreen(
            paddingValues = paddingValues,
            menus = menus,
            reviews = reviews,
            foodList = foodList,
            homeLazyListState = homeLazyListState,
            navigateToProductList = navigateToProductList,
            popularAddToCartClicked = { cart: Cart ->
                viewModel.insertCart(cart)
                viewModel.setAddToCartClicked(true)
            }
        )
    }
}