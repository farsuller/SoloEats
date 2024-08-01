package com.solodemo.main.presentations.dashboard.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.solo.ui.Elevation
import com.solo.util.formatToCurrency
import com.solodemo.main.components.RatingBar
import com.solodemo.main.model.Featured
import com.solodemo.supabase.model.Cart

@Composable
fun HomePopularCard(
    modifier: Modifier = Modifier,
    foodId: Int,
    foodName: String = "",
    foodPrice: String = "",
    foodImage: String = "",
    onAddButtonClicked: (Cart) -> Unit,
) {
    ElevatedCard(
        modifier = modifier.size(width = 270.dp, height = 200.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = Elevation.level5),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
        ) {
            Row(
                modifier = Modifier
                    .weight(2F)
                    .fillMaxSize(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start,
            ) {
                AsyncImage(
                    modifier = Modifier.weight(1F),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(foodImage)
                        .crossfade(true).build(),
                    contentDescription = "Banner Image",
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center,
                )

                Column(
                    modifier = Modifier
                        .weight(1.5F)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        modifier = Modifier,
                        text = foodName,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        textAlign = TextAlign.Start,
                    )

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = formatToCurrency(foodPrice.toDouble()),
                        fontFamily = MaterialTheme.typography.titleSmall.fontFamily,
                        fontSize = 13.sp,
                        lineHeight = 14.sp,
                        textAlign = TextAlign.Start,
                    )
                }
            }

            Row(
                modifier = Modifier
                    .weight(1F)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RatingBar()

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(color = Color(0xFFF44336)),
                ) {
                    IconButton(
                        onClick = {
                            onAddButtonClicked(
                                Cart(
                                    id = foodId,
                                    productName = foodName,
                                    productImage = foodImage,
                                    productPrice = foodPrice,
                                    productQuantity = 1,
                                    productPriceOriginal = foodPrice,
                                ),
                            )
                        },
                        modifier = Modifier,

                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Button",
                            tint = Color.White,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
internal fun CardProductPreview() {
    HomePopularCard(
        foodId = 0,
        foodImage = Featured.CheesyHavenDeluxe.imagePath,
        foodName = Featured.CheesyHavenDeluxe.title,
        foodPrice = Featured.CheesyHavenDeluxe.price,
        onAddButtonClicked = {},

    )
}
