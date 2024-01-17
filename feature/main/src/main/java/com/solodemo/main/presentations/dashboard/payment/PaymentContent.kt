package com.solodemo.main.presentations.dashboard.payment

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solodemo.main.presentations.dashboard.payment.components.PaymentWalletCard
import com.solodemo.main.presentations.dashboard.payment.components.RecentTransactionCard


@Composable
internal fun PaymentContent(
    onWalletClicked: () -> Unit,
    paddingValues: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding() / 2)
            .padding(bottom = paddingValues.calculateBottomPadding())
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Payment",
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontSize = 28.sp
                )

                Icon(imageVector = Icons.Outlined.Settings, contentDescription = null)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),

                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Our multi-layered\nsecurity keeps you safe",
                    maxLines = 2,
                    fontFamily = MaterialTheme.typography.titleSmall.fontFamily,
                    fontSize = 14.sp,
                    lineHeight = 18.sp
                )
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Outlined.Shield,
                    contentDescription = null
                )
            }


            Spacer(modifier = Modifier.size(15.dp))

            PaymentWalletCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(10.dp),
                title = "SoloEats Wallet",
                amount = " 0.00",
                onWalletClicked = onWalletClicked
            )

            Spacer(modifier = Modifier.size(15.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Recent Transactions",
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    fontSize = 18.sp
                )

                Box(
                    modifier = Modifier.background(
                        color = MaterialTheme.colorScheme.secondary,
                        shape = CircleShape
                    )
                ) {
                    Icon(
                        modifier = Modifier.padding(3.dp),
                        imageVector = Icons.Outlined.KeyboardArrowRight,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.surface
                    )
                }

            }
            Spacer(modifier = Modifier.size(15.dp))
            RecentTransactionCard()

        }
    }

}

@Preview(showBackground = true)
@Composable
internal fun PaymentWalletCardPreview() {
    PaymentContent(
        onWalletClicked = {},
        paddingValues = PaddingValues()

    )
}