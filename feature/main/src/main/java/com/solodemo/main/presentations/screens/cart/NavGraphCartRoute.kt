package com.solodemo.main.presentations.screens.cart

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes
import com.solo.components.state.RequestState
import com.solodemo.main.presentations.MainViewModel
import com.solodemo.supabase.domain.repository.Carts
import com.solodemo.supabase.model.Cart
import kotlinx.coroutines.flow.collectLatest

fun NavGraphBuilder.cartRoute(paddingValues: PaddingValues, carts: Carts,viewModel: MainViewModel) {
    composable(route = ScreensRoutes.Cart.route) {


        val cartViewModel = hiltViewModel<CartViewModel>()
        val context = LocalContext.current.applicationContext

        CartScreen(
            paddingValues = paddingValues,
            carts = carts,
            cartViewModel = cartViewModel,
            onSuccess = {
                Toast.makeText(
                    context, "Success! Your item has been deleted to your cart",
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.getCartList()
            },
            onError = {
                Toast.makeText(
                    context, "Failed! To delete item on your cart",
                    Toast.LENGTH_SHORT
                ).show()
            })
    }
}