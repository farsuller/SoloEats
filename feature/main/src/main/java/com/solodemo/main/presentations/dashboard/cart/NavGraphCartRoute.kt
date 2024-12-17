package com.solodemo.main.presentations.dashboard.cart

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes
import com.solodemo.main.presentations.dashboard.account.AccountState

fun NavGraphBuilder.cartRoute(
    paddingValues: PaddingValues,
    accountState: AccountState,
    navigateToPlaceOrderSuccess: () -> Unit,
) {
    composable(route = ScreensRoutes.Cart.route) {
        val viewModel = hiltViewModel<CartViewModel>()
        val cartState by viewModel.cartState.collectAsStateWithLifecycle()
        val isLoadingCartData by viewModel.isLoadingData.collectAsStateWithLifecycle()
        CartScreen(
            paddingValues = paddingValues,
            cartState = cartState,
            accountState = accountState,
            isLoadingCartData = isLoadingCartData,
            navigateToPlaceOrderSuccess = navigateToPlaceOrderSuccess,
            onDeleteItem = {
                viewModel.onEvent(CartEvent.DeleteCartItem(it))
            },
            placeOrderButtonClicked = {
                viewModel.onEvent(CartEvent.DeleteAllCartItem)
            },
            onQuantityChange = { cartItem, newQuantity ->
                viewModel.onEvent(CartEvent.UpdateCartQuantity(cartItem, newQuantity))
            },
        )
    }
}
