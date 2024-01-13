package com.solodemo.main.presentations.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solo.components.state.RequestState
import com.solo.ui.Elevation
import com.solo.util.formatToCurrency
import com.solodemo.main.presentations.screens.cart.components.CartCardItems
import com.solodemo.supabase.domain.repository.Carts
import com.solodemo.supabase.model.Cart


@Composable
internal fun CartContent(
    paddingValues: PaddingValues,
    carts: Carts
) {


    val deliveryFee by remember { mutableDoubleStateOf(59.00) }
    var cartList by remember { mutableStateOf(emptyList<Cart>()) }

    val subTotalPrice by remember(cartList) {
        derivedStateOf {
            // Filter out items with null productPrice, then sum the non-null values
            cartList
                .filter { it.productPrice != null }
                .sumOf { it.productPrice!!.toDouble() }
        }
    }
    val totalPrice by remember(subTotalPrice, deliveryFee) {
        derivedStateOf {
            // Sum up totalPrice and deliveryFee to get the total amount
            subTotalPrice + deliveryFee
        }
    }
    when (carts) {
        is RequestState.Loading -> {}
        is RequestState.Success -> {
            cartList = carts.data
        }

        is RequestState.Error -> {}
        else -> {}
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding() / 2)
            .padding(bottom = paddingValues.calculateBottomPadding())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, end = 15.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,

            ) {

            Column(
                modifier = Modifier
                    .weight(8F)
                    .fillMaxWidth()
                    .verticalScroll(state = rememberScrollState())
            ) {


                ElevatedCard(
                    modifier = Modifier,
                    elevation = CardDefaults.cardElevation(defaultElevation = Elevation.level2),
                    shape = RoundedCornerShape(13.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "Delivery Address",
                                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                                fontSize = 22.sp,
                            )

                            Text(
                                modifier = Modifier.padding(5.dp),
                                text = "Edit",
                                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                                fontSize = 15.sp,
                            )
                        }

                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "My Name",
                            fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                            fontSize = 15.sp,
                        )

                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "My Address",
                            fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                            fontSize = 15.sp,
                        )

                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "My Mobile Number",
                            fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                            fontSize = 15.sp,
                        )


                    }
                }

                Spacer(modifier = Modifier.size(15.dp))

                ElevatedCard(
                    modifier = Modifier,
                    elevation = CardDefaults.cardElevation(defaultElevation = Elevation.level2),
                    shape = RoundedCornerShape(13.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 15.dp),
                            text = "Order Summary",
                            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                            fontSize = 22.sp,
                        )

                        cartList.forEach { cartItems ->
                            CartCardItems(cartItems = cartItems)
                        }
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f)))

                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                modifier = Modifier.padding(5.dp),
                                text = "SubTotal",
                                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                                fontSize = 15.sp,
                            )

                            Text(
                                modifier = Modifier.padding(5.dp),
                                text = formatToCurrency(subTotalPrice),
                                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                            )

                        }


                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically) {

                            Text(
                                modifier = Modifier.padding(5.dp),
                                text = "Delivery fee",
                                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                                fontSize = 15.sp,
                            )
                            Text(

                                modifier = Modifier.padding(5.dp),
                                text = formatToCurrency(deliveryFee),
                                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                            )

                        }

                    }


                }


                Spacer(modifier = Modifier.size(15.dp))


                ElevatedCard(
                    modifier = Modifier,
                    elevation = CardDefaults.cardElevation(defaultElevation = Elevation.level2),
                    shape = RoundedCornerShape(13.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                    ) {

                        Text(
                            modifier = Modifier,
                            text = "Payment Details",
                            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                            fontSize = 22.sp,
                        )

                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "Cash on Delivery",
                            fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                            fontSize = 15.sp,
                        )

                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "Debit/Credit Card",
                            fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                            fontSize = 15.sp,
                        )

                    }
                }
                Spacer(modifier = Modifier.size(25.dp))
            }

            Box(
                modifier = Modifier
                    .weight(1.5F)
                    .padding(top = 10.dp, bottom = 10.dp)
            )
            {

                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "Total",
                            fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                            fontSize = 16.sp,
                        )
                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = formatToCurrency(totalPrice),
                            fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        )
                    }
                    Button(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)

                    ) {

                        Text(
                            text = "Place Order",
                            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            color = MaterialTheme.colorScheme.surface
                        )

                    }
                }

            }


        }
    }
}