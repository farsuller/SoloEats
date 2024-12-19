package com.solodemo.home.presentations.dashboard.cart.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solodemo.database.domain.model.Cart
import com.solodemo.home.components.QuantityButton

@Composable
fun QuantityUpdateCartButtons(
    cartItem: Cart,
    onQuantityChange: (Cart, Int) -> Unit,
) {
    var quantity by remember { mutableIntStateOf(cartItem.productDetails?.quantity ?: 1) }

    Row(
        modifier = Modifier.padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        QuantityButton(
            icon = Icons.Filled.Remove,
            contentDescription = "Minus Button",
            onClick = {
                if (quantity > 1) {
                    quantity--
                    onQuantityChange(cartItem, quantity)
                }
            },
        )

        Text(
            modifier = Modifier.padding(start = 25.dp, end = 25.dp),
            text = "$quantity",
            fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            color = MaterialTheme.colorScheme.onSurface,
        )
        QuantityButton(
            icon = Icons.Default.Add,
            contentDescription = "Add Button",
            onClick = {
                quantity++
                onQuantityChange(cartItem, quantity)
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
internal fun QuantityUpdateCartButtonsPreview() {
    QuantityUpdateCartButtons(
        cartItem = Cart(1),
        onQuantityChange = { _, _ -> },
    )
}
