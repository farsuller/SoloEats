package com.solodemo.main.presentations.screens.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solo.ui.Elevation
import com.solodemo.main.model.Featured
import com.solodemo.main.presentations.screens.cart.components.CartCardItems


@Composable
internal fun CartContent(
    paddingValues: PaddingValues
) {

    val burgerList = Featured.entries.toTypedArray()
    val scroll = rememberScrollState()
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
                    .verticalScroll(state = scroll)
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

                        burgerList.forEach { cartItems ->
                            CartCardItems(burger = cartItems)
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
                            text = "Payment Method",
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
                            text = "Debit / Credit Card",
                            fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                            fontSize = 15.sp,
                        )

                    }
                }
                Spacer(modifier = Modifier.size(25.dp))
            }




            Box(
                modifier = Modifier
                    .weight(0.8F)
                    .padding(top = 10.dp, bottom = 10.dp)
            )
            {
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