package com.solodemo.main.presentations

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes
import com.solo.components.state.RequestState
import kotlinx.coroutines.flow.collectLatest

fun NavGraphBuilder.mainRoute(
    onDataLoaded: () -> Unit,
    navigateToAuth: () -> Unit,
    navigateToProductList: (String) -> Unit
) {
    composable(route = ScreensRoutes.Main.route) {

        val context = LocalContext.current
        val viewModel = hiltViewModel<MainViewModel>()
        val menusList by viewModel.menus
        val user by viewModel.user
        val carts by viewModel.carts

        LaunchedEffect(key1 = true) {
            onDataLoaded()

            viewModel.cartState.collectLatest { data ->
                when (data) {
                    RequestState.Loading -> {}
                    is RequestState.Success -> {
                        Toast.makeText(context,"Success! Your item has been added to the cart",
                            Toast.LENGTH_SHORT).show()
                        viewModel.getCartList()
                    }

                    is RequestState.Error -> {
                        Toast.makeText(context,"Failed! Your item has been existing to the cart",
                            Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            }
        }

        MainScreen(
            menus = menusList,
            users = user,
            carts = carts,
            viewModel = viewModel,
            navigateToAuth = navigateToAuth,
            navigateToProductList = navigateToProductList
        )
    }
}