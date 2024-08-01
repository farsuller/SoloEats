package com.solodemo.main.presentations.dashboard.cart

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes
import com.solodemo.main.presentations.MainViewModel
import com.solodemo.supabase.domain.repository.Carts

fun NavGraphBuilder.cartRoute(
    paddingValues: PaddingValues,
    carts: Carts,
    viewModel: MainViewModel,
    navigateToPlaceOrderSuccess: () -> Unit,
) {
    composable(route = ScreensRoutes.Cart.route) {
        val cartViewModel = hiltViewModel<CartViewModel>()
        val context = LocalContext.current.applicationContext

        CartScreen(
            paddingValues = paddingValues,
            carts = carts,
            cartViewModel = cartViewModel,
            onSuccess = { message ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                viewModel.getCartList()
            },
            onError = { message ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            },
            navigateToPlaceOrderSuccess = navigateToPlaceOrderSuccess,
        )
    }
}
