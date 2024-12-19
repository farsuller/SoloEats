package com.solodemo.home.presentations.placeorder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.solo.components.R

@Composable
fun PlaceOrderScreen(
    onNavigateToMain: () -> Unit,
) {
    val lottieDelivery by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.delivery1))

    val progress by animateLottieCompositionAsState(
        composition = lottieDelivery,
        iterations = LottieConstants.IterateForever,
    )

    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {},
        content = { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = paddingValues.calculateTopPadding())
                    .padding(bottom = paddingValues.calculateBottomPadding()),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    LottieAnimation(
                        modifier = Modifier
                            .height(300.dp)
                            .weight(1F),
                        composition = lottieDelivery,
                        progress = { progress },
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1F),
                        verticalArrangement = Arrangement.Top,
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Yey! Success",
                            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp),
                            text = "Your order has been successfully processed. Please await delivery to receive your food.",
                            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp),
                            text = "Thank you. Order with us again!.",
                            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                        )
                    }

                    Box(modifier = Modifier.weight(0.2F)) {
                        Button(
                            onClick = { onNavigateToMain() },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(5.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),

                        ) {
                            Text(
                                text = "Back to Dashboard",
                                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                color = MaterialTheme.colorScheme.surface,
                            )
                        }
                    }
                }
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun PlaceOrderScreenPreview() {
    PlaceOrderScreen(onNavigateToMain = { })
}
