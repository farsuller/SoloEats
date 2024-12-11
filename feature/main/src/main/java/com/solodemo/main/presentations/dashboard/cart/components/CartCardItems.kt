package com.solodemo.main.presentations.dashboard.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.solo.components.formatToCurrency
import com.solodemo.supabase.domain.model.Cart

@Composable
fun CartCardItems(cartItems: Cart, onClickUpdate: (Cart) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier.size(100.dp),
                model = cartItems.productImage,
                contentDescription = "Food Image",
            )

            Column(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .padding(horizontal = 20.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    cartItems.productName?.let { pName ->
                        Text(
                            modifier = Modifier.weight(1F),
                            text = pName,
                            fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Start,
                        )
                    }
                }

                cartItems.productPriceOriginal?.let { pPrice ->
                    Text(
                        text = formatToCurrency(
                            (
                                pPrice.toDouble() * (
                                    cartItems.productQuantity?.toDouble()
                                        ?: 0.0
                                    )
                                ),
                        ),
                        fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }

                cartItems.productQuantity?.let { pQuantity ->
                    Text(
                        text = "x$pQuantity",
                        fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        }

        QuantityUpdateCartButtons(
            cartItems = cartItems,
            onClickUpdate = { updateCart: Cart ->
                onClickUpdate(updateCart)
            },
        )
    }
}

@Composable
fun QuantityUpdateCartButtons(
    cartItems: Cart,
    onClickUpdate: (Cart) -> Unit,
    modifier: Modifier = Modifier,
) {
    var quantity by remember { mutableIntStateOf(cartItems.productQuantity ?: 1) }

    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Row(
            modifier = Modifier
                .weight(0.5F)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = Color(0xFFF44336)),
            ) {
                IconButton(
                    onClick = { quantity++ },
                    modifier = Modifier,

                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Button",
                        tint = Color.White,
                    )
                }
            }

            Text(
                modifier = Modifier.padding(start = 25.dp, end = 25.dp),
                text = "$quantity",
                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = Color(0xFFF44336)),
            ) {
                IconButton(
                    onClick = {
                        if (quantity > 1) quantity--
                    },
                    modifier = Modifier,

                ) {
                    Icon(
                        imageVector = Icons.Filled.Remove,
                        contentDescription = "Minus Button",
                        tint = Color.White,
                    )
                }
            }
        }

        Button(
            onClick = {
                onClickUpdate(
                    Cart(
                        id = cartItems.id,
                        productName = cartItems.productName,
                        productImage = cartItems.productImage,
                        productPrice = (cartItems.productPriceOriginal!!.toDouble() * quantity.toDouble()).toString(),
                        productQuantity = quantity,
                        productPriceOriginal = cartItems.productPriceOriginal,
                    ),
                )
            },
            modifier = Modifier
                .weight(0.35F)
                .fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),

        ) {
            Text(
                text = "Update",
                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                color = MaterialTheme.colorScheme.surface,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ProductCardItemsPreview() {
    QuantityUpdateCartButtons(
        modifier = Modifier.height(50.dp),
        cartItems = Cart(1),
        onClickUpdate = {},
    )
}
