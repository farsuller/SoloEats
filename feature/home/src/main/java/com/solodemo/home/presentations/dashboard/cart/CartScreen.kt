package com.solodemo.home.presentations.dashboard.cart

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solo.components.contents.EmptyContent
import com.solodemo.database.domain.model.Cart
import com.solodemo.home.presentations.dashboard.account.AccountState
import com.solodemo.home.presentations.dashboard.cart.components.CouponsContent
import com.solodemo.home.presentations.dashboard.cart.components.CutleryRequestContent
import com.solodemo.home.presentations.dashboard.cart.components.DeliveryAddressContent
import com.solodemo.home.presentations.dashboard.cart.components.OrderSummaryContent
import com.solodemo.home.presentations.dashboard.cart.components.PaymentMethodContent
import com.solodemo.home.presentations.dashboard.cart.components.TotalAndPlaceOrderButtonHolder

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun CartScreen(
    paddingValues: PaddingValues,
    cartState: CartState,
    accountState: AccountState,
    isLoadingCartData: Boolean,
    navigateToPlaceOrderSuccess: () -> Unit,
    placeOrderButtonClicked: () -> Unit,
    onDeleteItem: (Cart) -> Unit,
    onQuantityChange: (Cart, Int) -> Unit,
) {
    if (isLoadingCartData) {
        CircularProgressIndicator()
    } else {
        when {
            cartState.cartList?.isEmpty() == true -> {
                EmptyContent(title = "Your Cart is Empty", subtitle = "Select Order Now")
            }

            cartState.cartList != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = paddingValues.calculateBottomPadding()),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                        ) {
                            item {
                                DeliveryAddressContent(accountState = accountState)

                                Spacer(modifier = Modifier.size(10.dp))

                                OrderSummaryContent(
                                    cartState = cartState,
                                    onDeleteItem = onDeleteItem,
                                    onQuantityChange = onQuantityChange,
                                )

                                Spacer(modifier = Modifier.size(15.dp))

                                CutleryRequestContent()

                                Spacer(modifier = Modifier.size(15.dp))

                                PaymentMethodContent()

                                Spacer(modifier = Modifier.size(25.dp))

                                CouponsContent(cartState = cartState)

                                Spacer(modifier = Modifier.size(15.dp))
                            }
                        }

                        Box(
                            modifier = Modifier
                                .padding(10.dp)
                                .weight(0.15f),
                        ) {
                            TotalAndPlaceOrderButtonHolder(
                                placeOrderButtonClicked = placeOrderButtonClicked,
                                navigateToPlaceOrderSuccess = navigateToPlaceOrderSuccess,
                                cartState = cartState,
                            )
                        }
                    }
                }
            }
        }
    }
}
