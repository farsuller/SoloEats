package com.solodemo.home.presentations.dashboard.cart

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes
import com.solodemo.home.presentations.dashboard.account.AccountState
import com.stevdzasan.messagebar.ContentWithMessageBar
import com.stevdzasan.messagebar.MessageBarPosition
import com.stevdzasan.messagebar.rememberMessageBarState

fun NavGraphBuilder.cartRoute(
    paddingValues: PaddingValues,
    accountState: AccountState,
    navigateToPlaceOrderSuccess: () -> Unit,
) {
    composable(route = ScreensRoutes.Cart.route) {
        val viewModel = hiltViewModel<CartViewModel>()
        val cartState by viewModel.cartState.collectAsStateWithLifecycle()
        val isLoadingCartData by viewModel.isLoadingData.collectAsStateWithLifecycle()

        val messageBarState = rememberMessageBarState()
        ContentWithMessageBar(
            messageBarState = messageBarState,
            showCopyButton = false,
            position = MessageBarPosition.BOTTOM,
        ) {
            CartScreen(
                paddingValues = paddingValues,
                cartState = cartState,
                accountState = accountState,
                isLoadingCartData = isLoadingCartData,
                navigateToPlaceOrderSuccess = navigateToPlaceOrderSuccess,
                onDeleteItem = {
                    viewModel.onEvent(CartEvent.DeleteCartItem(it))
                    messageBarState.addSuccess("Successfully Deleted ${it.productDetails?.name}")
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
}
