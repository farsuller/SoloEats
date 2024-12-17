package com.solodemo.main.presentations.products.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.solo.components.Elevation
import com.solo.components.formatToCurrency
import com.solodemo.database.domain.model.Cart
import com.solodemo.main.components.RatingBar
import com.solodemo.network.domain.model.Food

@Composable
fun ProductsCardItems(
    food: Food,
    insertCart: (Cart) -> Unit,
) {
    var isFavourite by remember { mutableStateOf(false) }
    val updatedIsFavourite = rememberUpdatedState(isFavourite)

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = Elevation.level4,
        ),
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .padding(top = 10.dp),
        shape = RoundedCornerShape(13.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 10.dp),
        ) {
            Row {
                Column(horizontalAlignment = Alignment.End) {
                    SubcomposeAsyncImage(
                        modifier = Modifier.size(100.dp),
                        model = food.image,
                        contentDescription = "Food Image",
                    )
                    IconButton(
                        modifier = Modifier,
                        onClick = {
                            isFavourite = !isFavourite
                        },
                    ) {
                        if (updatedIsFavourite.value) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Favorite Icon",
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Outlined.FavoriteBorder,
                                contentDescription = "Favorite Icon",
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .padding(horizontal = 10.dp),
                ) {
                    Text(
                        text = "${food.name}",
                        fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = MaterialTheme.colorScheme.onSurface,
                    )

                    Text(
                        text = formatToCurrency(food.price?.toDouble() ?: 0.0),
                        fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = MaterialTheme.colorScheme.onSurface,
                    )

                    Text(
                        modifier = Modifier.padding(top = 15.dp),
                        text = "${food.description}",
                        fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = MaterialTheme.colorScheme.onSurface,
                    )

                    RatingBar(
                        modifier = Modifier.padding(top = 5.dp),
                        starsCount = food.starReview,
                    )
                }
            }
            QuantityAddCartButtons(
                food = food,
                addToCartClicked = { cart ->
                    insertCart(cart)
                },
            )
        }
    }
}
