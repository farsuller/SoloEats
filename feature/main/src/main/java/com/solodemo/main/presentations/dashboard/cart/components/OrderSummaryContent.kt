package com.solodemo.main.presentations.dashboard.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solo.components.md_theme_light_delete_swipe
import com.solodemo.database.domain.model.Cart
import com.solodemo.main.presentations.dashboard.cart.CartState
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun OrderSummaryContent(
    cartState: CartState,
    onDeleteItem: (Cart) -> Unit = {},
    onQuantityChange: (Cart, Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onPrimary),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
        ) {
            OrderSummaryHeader()

            when {
                cartState.cartList != null -> {
                    cartState.cartList.forEach { cartItems ->
                        val swipeDelete = SwipeAction(
                            onSwipe = { onDeleteItem(cartItems) },
                            icon = {
                                Icon(
                                    modifier = Modifier.padding(16.dp),
                                    imageVector = Icons.Outlined.Delete,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.surface,
                                )
                            },
                            background = md_theme_light_delete_swipe,
                        )
                        SwipeableActionsBox(
                            swipeThreshold = 200.dp,
                            endActions = listOf(swipeDelete),
                        ) {
                            CartCardItems(
                                cartItem = cartItems,
                                onQuantityChange = onQuantityChange,
                            )
                        }
                    }
                }

                cartState.errorMessage != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(text = "Error: ${cartState.errorMessage}")
                    }
                }
            }

            Spacer(modifier = Modifier.size(20.dp))

            SubtotalDeliveryContent(cartState)
        }
    }
}
