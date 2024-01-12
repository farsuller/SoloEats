package com.solodemo.main.presentations.products.components

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.skydoves.orbital.Orbital
import com.skydoves.orbital.animateBounds
import com.skydoves.orbital.rememberMovableContentOf
import com.solo.ui.Elevation
import com.solo.util.clickableWithoutRipple
import com.solodemo.main.model.Food

@Composable
fun ProductsCardItems(foodList: Food) {

    var isFavourite by remember { mutableStateOf(false) }
    val updatedIsFavourite = rememberUpdatedState(isFavourite)

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = Elevation.level4
        ),
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .padding(top = 10.dp),
        shape = RoundedCornerShape(13.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 10.dp)
        ) {

            var expanded by rememberSaveable { mutableStateOf(false) }
            AnimatedVisibility(remember { MutableTransitionState(false) }.apply {
                targetState = true
            },
                enter = fadeIn(tween(durationMillis = 300)),
                exit = fadeOut(tween(durationMillis = 300))
            ) {
                Orbital(modifier = Modifier
                    .fillMaxWidth()
                    .clickableWithoutRipple(
                        interactionSource = MutableInteractionSource(),
                        onClick = { expanded = !expanded }
                    )
                ) {


                    val showProductDetails = rememberMovableContentOf {
                        Column(
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .padding(horizontal = if (expanded) 20.dp else 10.dp)
                                .animateBounds(
                                    sizeAnimationSpec = tween(durationMillis = 300),
                                    positionAnimationSpec = tween(durationMillis = 300)
                                )
                        ) {

                            Text(
                                text = foodList.foodName,
                                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            Text(
                                text = foodList.price,
                                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                color = MaterialTheme.colorScheme.onSurface
                            )




                            if (expanded) {
                                Text(
                                    modifier = Modifier.padding(top = 15.dp),
                                    text = foodList.foodDescription,
                                    fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            } else {
                                Text(
                                    modifier = Modifier.padding(top = 10.dp),
                                    text = foodList.foodDescription,
                                    fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            RatingBar(
                                modifier = Modifier.padding(top = 5.dp),
                                starsCount = foodList.starReview
                            )
                        }
                    }

                    val showProductImage = rememberMovableContentOf {

                        Column(horizontalAlignment = Alignment.End) {
                            AsyncImage(
                                modifier = Modifier
                                    .animateBounds(
                                        modifier = if (expanded) {
                                            Modifier.fillMaxWidth()
                                                .height(300.dp)
                                        } else Modifier.size(
                                            100.dp
                                        )
                                    ),
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(foodList.foodImage)
                                    .crossfade(true).build(),
                                contentDescription = "Food Image"
                            )
                            IconButton(
                                modifier = Modifier,
                                onClick = {
                                    isFavourite = !isFavourite
                                }) {
                                if(updatedIsFavourite.value){
                                    Icon(
                                        imageVector = Icons.Filled.Favorite,
                                        contentDescription = "Favorite Icon")
                                }
                                else{
                                    Icon(
                                        imageVector = Icons.Outlined.FavoriteBorder,
                                        contentDescription = "Favorite Icon")
                                }
                            }
                        }

                    }

                    if (expanded) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .clip(RoundedCornerShape(10.dp))
                        ) {

                            showProductImage()
                            showProductDetails()
                            QuantityAddCartButtons(foodList = foodList)
                        }
                    } else {
                        Column {
                            Row {
                                showProductImage()
                                showProductDetails()
                            }
                            QuantityAddCartButtons(foodList = foodList)
                        }

                    }

                }
            }
        }
    }
}

@Composable
fun QuantityAddCartButtons(foodList: Food) {

    var quantity by remember { mutableIntStateOf(1) }

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Row(
            modifier = Modifier
                .weight(0.5F)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = Color(0xFFF44336))
            ) {
                IconButton(
                    onClick = { quantity++ },
                    modifier = Modifier

                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Button",
                        tint = Color.White
                    )
                }
            }

            Text(
                modifier = Modifier.padding(start = 25.dp, end = 25.dp),
                text = "$quantity",
                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                color = MaterialTheme.colorScheme.onSurface
            )

            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = Color(0xFFF44336))
            ) {
                IconButton(
                    onClick = {
                        if (quantity > 1) quantity--
                     },
                    modifier = Modifier

                ) {
                    Icon(
                        imageVector = Icons.Filled.Remove,
                        contentDescription = "Minus Button",
                        tint = Color.White
                    )
                }
            }
        }

        Button(
            onClick = {},
            modifier = Modifier
                .weight(0.35F)
                .fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)

        ) {

            Text(
                text = "Add to Cart",
                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                color = MaterialTheme.colorScheme.surface
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ProductCardItemsPreview() {
    QuantityAddCartButtons(foodList = Food())
}