package com.solodemo.main.presentations.dashboard.cart

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solo.components.component.ShimmerListItem
import com.solo.components.contents.EmptyContent
import com.solo.components.state.RequestState
import com.solo.ui.Elevation
import com.solo.ui.md_theme_light_delete_swipe
import com.solo.util.clickableWithoutRipple
import com.solo.util.formatToCurrency
import com.solodemo.main.model.Coupons
import com.solodemo.main.presentations.dashboard.cart.components.CartCardItems
import com.solodemo.main.presentations.dashboard.cart.components.CouponItemCard
import com.solodemo.supabase.domain.repository.Carts
import com.solodemo.supabase.model.Cart
import kotlinx.coroutines.launch
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox


@SuppressLint("UnrememberedMutableInteractionSource")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CartContent(
    paddingValues: PaddingValues,
    carts: Carts,
    cartViewModel: CartViewModel,
    onSuccess: (String) -> Unit,
    onError: (String) -> Unit,
    navigateToPlaceOrderSuccess: () -> Unit
) {
    val isCartNotEmpty = remember { mutableStateOf(false) }

    val tooltipState = rememberTooltipState()
    val scope = rememberCoroutineScope()

    if (carts is RequestState.Success) {
        isCartNotEmpty.value = carts.data.isNotEmpty()
        cartViewModel.setCartList(carts.data)
    }

    if (isCartNotEmpty.value) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding() / 2)
                .padding(bottom = paddingValues.calculateBottomPadding())
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
                        .verticalScroll(state = rememberScrollState())
                ) {

                    DeliveryAddressContent(cartViewModel)

                    Spacer(modifier = Modifier.size(15.dp))

                    ElevatedCard(
                        modifier = Modifier,
                        elevation = CardDefaults.cardElevation(defaultElevation = Elevation.level2),
                        shape = RoundedCornerShape(13.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp)
                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 15.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = "Order Summary",
                                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                                    fontSize = 22.sp,
                                )

                                TooltipBox(
                                    positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
                                    tooltip = {
                                        PlainTooltip {
                                            Text("Swipe to Delete")
                                        }
                                    },
                                    state = tooltipState
                                ) {

                                    Icon(
                                        modifier = Modifier.clickableWithoutRipple(
                                            interactionSource = MutableInteractionSource(),
                                            onClick = { scope.launch { tooltipState.show() } }
                                        ),
                                        imageVector = Icons.Filled.Info,
                                        contentDescription = null
                                    )
                                }
                            }

                            OrderSummaryContent(
                                carts = carts,
                                onSuccess = onSuccess,
                                onError = onError,
                                cartViewModel = cartViewModel
                            )

                            SubtotalDeliveryContent(cartViewModel)
                        }

                    }

                    Spacer(modifier = Modifier.size(15.dp))

                    PaymentMethodContent()

                    Spacer(modifier = Modifier.size(25.dp))

                    CouponsContent()

                    Spacer(modifier = Modifier.size(15.dp))
                }

                Box(
                    modifier = Modifier
                        .weight(1.5F)
                        .padding(top = 10.dp, bottom = 10.dp)
                )
                {

                    TotalAndPlaceOrderButtonHolder(
                        cartViewModel = cartViewModel,
                        navigateToPlaceOrderSuccess = navigateToPlaceOrderSuccess
                    )

                }


            }
        }
    } else {
        EmptyContent(title = "Your Cart is Empty", subtitle = "Select Order Now")
    }

}

@Composable
private fun DeliveryAddressContent(cartViewModel: CartViewModel) {
    ElevatedCard(
        modifier = Modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = Elevation.level2),
        shape = RoundedCornerShape(13.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier,
                    text = "Delivery Address",
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontSize = 22.sp,
                )


            }

            Text(
                modifier = Modifier.padding(5.dp),
                text = "Name: ${cartViewModel.accountState.name}",
                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                fontSize = 15.sp,
            )

            Text(
                modifier = Modifier.padding(5.dp),
                text = "Address: ${cartViewModel.accountState.address}",
                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                fontSize = 15.sp,
            )

            Text(
                modifier = Modifier.padding(5.dp),
                text = "Mobile Number: ${cartViewModel.accountState.mobileNumber}",
                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                fontSize = 15.sp,
            )
        }
    }
}

@Composable
private fun SubtotalDeliveryContent(cartViewModel: CartViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f))
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(5.dp),
            text = "SubTotal",
            fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
            fontSize = 15.sp,
        )

        Text(
            modifier = Modifier.padding(5.dp),
            text = formatToCurrency(cartViewModel.subTotalPrice.value),
            fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
        )

    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            modifier = Modifier.padding(5.dp),
            text = "Delivery fee",
            fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
            fontSize = 15.sp,
        )
        Text(

            modifier = Modifier.padding(5.dp),
            text = formatToCurrency(cartViewModel.deliveryFee.value),
            fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
        )

    }
}

@Composable
private fun PaymentMethodContent() {
    ElevatedCard(
        modifier = Modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = Elevation.level2),
        shape = RoundedCornerShape(13.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {

            Text(
                modifier = Modifier,
                text = "Payment Details",
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                fontSize = 22.sp,
            )

            Text(
                modifier = Modifier.padding(5.dp),
                text = "Cash on Delivery",
                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                fontSize = 15.sp,
            )

        }
    }
}

@Composable
private fun CouponsContent() {

    var selectedCouponItem by remember { mutableStateOf<Coupons?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()

    ) {

        Text(
            modifier = Modifier,
            text = "Coupons and Promo",
            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
            fontSize = 22.sp,
        )

        LazyRow {
            items(Coupons.entries.toTypedArray()) { couponItem ->
                CouponItemCard(
                    coupons = couponItem,
                    isSelected = selectedCouponItem == couponItem
                ) {
                    selectedCouponItem = couponItem
                }
            }
        }

    }

}


@Composable
private fun TotalAndPlaceOrderButtonHolder(
    cartViewModel: CartViewModel,
    navigateToPlaceOrderSuccess: () -> Unit
) {
    val context = LocalContext.current.applicationContext
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(5.dp),
                text = "Total",
                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                fontSize = 16.sp,
            )
            Text(
                modifier = Modifier.padding(5.dp),
                text = formatToCurrency(cartViewModel.totalPrice.value),
                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
            )
        }
        Button(
            onClick = {
                cartViewModel.deleteAllCartItem(
                    onSuccess = { navigateToPlaceOrderSuccess() },
                    onError = {
                        Toast.makeText(
                            context,
                            "Something went wrong on deletion",
                            Toast.LENGTH_SHORT
                        ).show()
                    })
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)

        ) {

            Text(
                text = "Place Order",
                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                color = MaterialTheme.colorScheme.surface
            )

        }
    }
}

@Composable
fun OrderSummaryContent(
    carts: Carts,
    onSuccess: (String) -> Unit,
    onError: (String) -> Unit,
    cartViewModel: CartViewModel
) {

    val cartLoadCount = remember { mutableIntStateOf(0) }
    when (carts) {
        RequestState.Loading -> {
            repeat(cartLoadCount.intValue) {
                ShimmerListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }
        }

        is RequestState.Success -> {
            cartLoadCount.intValue = carts.data.size
            carts.data.forEach { cartItems ->
                val swipeDelete = SwipeAction(
                    onSwipe = {
                        cartViewModel.deleteCartById(
                            id = cartItems.id,
                            onSuccess = onSuccess,
                            onError = onError
                        )
                    },
                    icon = {
                        Icon(
                            modifier = Modifier.padding(16.dp),
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.surface
                        )
                    },
                    background = md_theme_light_delete_swipe
                )
                SwipeableActionsBox(
                    swipeThreshold = 200.dp,
                    endActions = listOf(swipeDelete)
                ) {
                    CartCardItems(cartItems = cartItems, onClickUpdate = { updateCart: Cart ->
                        cartViewModel.updateCartById(
                            id = cartItems.id,
                            cart = updateCart,
                            onSuccess = onSuccess,
                            onError = onError
                        )
                    })

                }
            }

        }

        is RequestState.Error -> {}

        else -> {}
    }
}