package com.solodemo.main.presentations.dashboard.cart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.solo.components.R
import com.solo.components.formatToCurrency
import com.solodemo.database.domain.model.Cart
import com.solodemo.database.domain.model.ProductDetails

@Composable
fun CartCardItems(
    cartItem: Cart,
    onQuantityChange: (Cart, Int) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier.size(80.dp),
                model = cartItem.productDetails?.image,
                contentDescription = "Food Image",
                error = {
                    if (LocalInspectionMode.current) {
                        LocalProductPreview()
                    }
                },
            )

            Column(modifier = Modifier.padding(all = 10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    cartItem.productDetails?.name?.let { pName ->
                        Text(
                            modifier = Modifier.weight(1F),
                            text = pName,
                            fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 17.sp,
                            textAlign = TextAlign.Start,
                        )
                    }
                }

                cartItem.productDetails?.originalPrice?.let { pPrice ->
                    Text(
                        text = formatToCurrency((pPrice.toDouble() * (cartItem.productDetails?.quantity?.toDouble() ?: 0.0))),
                        fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    cartItem.productDetails?.quantity?.let { pQuantity ->
                        Text(
                            text = "x$pQuantity",
                            fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }

                    QuantityUpdateCartButtons(
                        cartItem = cartItem,
                        onQuantityChange = onQuantityChange,
                    )
                }
            }
        }
    }
}

@Composable
private fun LocalProductPreview() {
    Image(
        painter = painterResource(id = R.drawable.soloeats_logo),
        contentDescription = "",
        contentScale = ContentScale.Crop,
    )
}

@Preview(showBackground = true)
@Composable
internal fun ProductCardItemsPreview() {
    CartCardItems(
        cartItem = Cart(
            1,
            productDetails = ProductDetails(
                name = "Cheesy Haven Deluxe",
                image = "file:///android_asset/images/burger/CheesyHavenDeluxe.png",
                price = "340.00",
                quantity = 1,
                originalPrice = "340.00",
            ),
        ),
        onQuantityChange = { _, _ -> },
    )
}
