package com.solodemo.main.presentations.dashboard.cart.components

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.skydoves.orbital.Orbital
import com.skydoves.orbital.animateBounds
import com.skydoves.orbital.rememberMovableContentOf
import com.solo.util.clickableWithoutRipple
import com.solo.util.formatToCurrency
import com.solodemo.supabase.model.Cart

@Composable
fun CartCardItems(cartItems: Cart, onClickUpdate: (Cart) -> Unit) {

    Box(modifier = Modifier.fillMaxSize()) {

        var expanded by rememberSaveable { mutableStateOf(false) }
        AnimatedVisibility(remember { MutableTransitionState(false) }.apply {
            targetState = true
        },
            enter = fadeIn(tween(durationMillis = 300)),
            exit = fadeOut(tween(durationMillis = 300))
        ) {
            Orbital(modifier = Modifier.fillMaxWidth()) {


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

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            cartItems.productName?.let { pName ->
                                Text(
                                    modifier = Modifier.weight(1F),
                                    text = pName,
                                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    textAlign = TextAlign.Start
                                )
                            }


                            if (!expanded) {
                                Text(
                                    modifier = Modifier
                                        .weight(0.5F)
                                        .clickableWithoutRipple(
                                            interactionSource = MutableInteractionSource(),
                                            onClick = { expanded = !expanded }
                                        ),
                                    text = "Edit",
                                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    textAlign = TextAlign.End
                                )
                            }

                        }


                        cartItems.productPriceOriginal?.let { pPrice ->
                            Text(
                                text = formatToCurrency(
                                    (pPrice.toDouble() * (cartItems.productQuantity?.toDouble()
                                        ?: 0.0))
                                ),
                                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }

                        cartItems.productQuantity?.let { pQuantity ->
                            Text(
                                text = "x$pQuantity",
                                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }

                    }
                }

                val showProductImage = rememberMovableContentOf {
                    AsyncImage(
                        modifier = Modifier
                            .animateBounds(
                                modifier = if (expanded) {
                                    Modifier.size(100.dp)
                                } else Modifier.size(50.dp)
                            ),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(cartItems.productImage)
                            .crossfade(true).build(),
                        contentDescription = "Food Image"
                    )
                }

                if (expanded) {
                    Column {
                        Row {
                            showProductImage()
                            showProductDetails()
                        }
                        QuantityUpdateCartButtons(
                            cartItems = cartItems,
                            onClickUpdate = { updateCart: Cart ->
                                expanded = !expanded
                                onClickUpdate(updateCart)
                            })
                    }
                } else {
                    Column {
                        Row {
                            showProductImage()
                            showProductDetails()
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun QuantityUpdateCartButtons(cartItems: Cart, onClickUpdate: (Cart) -> Unit) {

    var quantity by remember { mutableIntStateOf(cartItems.productQuantity ?: 1) }

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
            onClick = {
                onClickUpdate(
                    Cart(
                        id = cartItems.id,
                        productName = cartItems.productName,
                        productImage = cartItems.productImage,
                        productPrice = (cartItems.productPriceOriginal!!.toDouble() * quantity.toDouble()).toString(),
                        productQuantity = quantity,
                        productPriceOriginal = cartItems.productPriceOriginal
                    )
                )
            },
            modifier = Modifier
                .weight(0.35F)
                .fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)

        ) {

            Text(
                text = "Update",
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
    QuantityUpdateCartButtons(cartItems = Cart(1), onClickUpdate = {})
}