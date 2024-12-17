package com.solodemo.main.presentations.products.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solodemo.database.domain.model.Cart
import com.solodemo.database.domain.model.ProductDetails
import com.solodemo.network.domain.model.Food

@Composable
fun QuantityAddCartButtons(food: Food, addToCartClicked: (Cart) -> Unit) {
    var quantity by remember { mutableIntStateOf(1) }

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
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
                addToCartClicked(
                    Cart(
                        id = food.id,
                        productDetails = ProductDetails(
                            name = food.name,
                            image = food.image,
                            price = (food.price?.toDouble()?.times(quantity.toDouble())).toString(),
                            quantity = quantity,
                            originalPrice = food.price,
                        ),
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
                text = "Add to Cart",
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
    QuantityAddCartButtons(food = Food(), addToCartClicked = {})
}
