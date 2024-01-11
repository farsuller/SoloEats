package com.solodemo.main.presentations.screens.payment.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solo.ui.Elevation

@Composable
fun PaymentWalletCard(
    modifier: Modifier = Modifier,
    title: String = "",
    amount: String = "",
    onWalletClicked: () -> Unit
) {
    ElevatedCard(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = Elevation.level5),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Column(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {

                Column(
                    modifier = Modifier
                        .weight(0.1F)
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        text = title,
                        fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                    )

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = amount,
                        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                        fontSize = 16.sp,
                        lineHeight = 14.sp,
                        textAlign = TextAlign.Start,
                    )
                }

            }
        }
    }
}


@Preview(showBackground = false)
@Composable
internal fun PaymentWalletCardPreview() {
    PaymentWalletCard(
        title = "SoloEats Wallet",
        amount = "₱ 0.00",
        onWalletClicked = {}

    )
}