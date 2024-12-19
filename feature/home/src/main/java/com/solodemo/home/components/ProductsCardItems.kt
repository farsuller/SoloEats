package com.solodemo.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.solo.components.Constants
import com.solo.components.Elevation
import com.solo.components.formatToCurrency
import com.solodemo.database.domain.model.Cart
import com.solodemo.database.domain.model.ProductDetails
import com.solodemo.network.domain.model.Food

@Composable
fun ProductsCardItems(
    food: Food,
    insertCart: (Cart) -> Unit,
    showRating: Boolean = false,
    showAddQuantityMinusButton: Boolean = false,
) {
    var quantity by remember { mutableIntStateOf(1) }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = Elevation.level4),
        modifier = Modifier
            .padding(10.dp)
            .size(width = 200.dp, height = 280.dp),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier.size(100.dp),
                model = food.image,
                contentDescription = "Food Image",
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${food.name}",
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    lineHeight = 15.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Start,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = formatToCurrency(food.price?.toDouble() ?: 0.0),
                    fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                if (showRating) RatingBar()
            }

            if (showAddQuantityMinusButton) {
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    QuantityButton(
                        icon = Icons.Filled.Remove,
                        contentDescription = "Minus Button",
                        onClick = {
                            if (quantity > 1) {
                                quantity--
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
                        },
                    )
                }
            }

            AddToCartButton(
                addToCartClicked = {
                    insertCart(
                        Cart(
                            id = food.id,
                            productDetails = ProductDetails(
                                name = food.name,
                                image = food.image,
                                price = (
                                    food.price?.toDouble()
                                        ?.times(quantity.toDouble())
                                    ).toString(),
                                quantity = quantity,
                                originalPrice = food.price,
                            ),
                        ),
                    )
                },
            )
        }
    }
}

@Preview
@Composable
internal fun ProductCardItemsPreview() {
    ProductsCardItems(
        food = Food(
            id = 1,
            name = "Burger Burger Burger Burger",
            image = Constants.StaticImages.CheesyHavenDeluxe,
            price = "100.0",
            starReview = 4,
        ),
        insertCart = {},
        showRating = true,
        showAddQuantityMinusButton = true,
    )
}
