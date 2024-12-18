package com.solodemo.main.presentations.dashboard.cart

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solo.components.Elevation
import com.solo.components.contents.EmptyContent
import com.solodemo.database.domain.model.Cart
import com.solodemo.main.presentations.dashboard.account.AccountState
import com.solodemo.main.presentations.dashboard.cart.components.CouponsContent
import com.solodemo.main.presentations.dashboard.cart.components.DeliveryAddressContent
import com.solodemo.main.presentations.dashboard.cart.components.OrderSummaryContent
import com.solodemo.main.presentations.dashboard.cart.components.OrderSummaryHeader
import com.solodemo.main.presentations.dashboard.cart.components.PaymentMethodContent
import com.solodemo.main.presentations.dashboard.cart.components.SubtotalDeliveryContent
import com.solodemo.main.presentations.dashboard.cart.components.TotalAndPlaceOrderButtonHolder

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
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 15.dp, end = 15.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top,

                    ) {
                        Column(
                            modifier = Modifier
                                .weight(8F)
                                .fillMaxWidth()
                                .verticalScroll(state = rememberScrollState()),
                        ) {
                            DeliveryAddressContent(accountState = accountState)

                            Spacer(modifier = Modifier.size(15.dp))

                            ElevatedCard(
                                modifier = Modifier,
                                elevation = CardDefaults.cardElevation(defaultElevation = Elevation.level2),
                                shape = RoundedCornerShape(13.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(15.dp),
                                ) {
                                    OrderSummaryHeader()

                                    OrderSummaryContent(
                                        cartState = cartState,
                                        onDeleteItem = onDeleteItem,
                                        onQuantityChange = onQuantityChange,
                                    )

                                    Spacer(modifier = Modifier.size(20.dp))

                                    SubtotalDeliveryContent(cartState)
                                }
                            }

                            Spacer(modifier = Modifier.size(15.dp))

                            PaymentMethodContent()

                            Spacer(modifier = Modifier.size(25.dp))

                            CouponsContent(cartState = cartState)

                            Spacer(modifier = Modifier.size(15.dp))
                        }

                        Box(
                            modifier = Modifier
                                .weight(1.5F)
                                .padding(top = 10.dp, bottom = 10.dp),
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
