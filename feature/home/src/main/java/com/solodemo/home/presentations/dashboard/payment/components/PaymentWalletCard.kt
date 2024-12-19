package com.solodemo.home.presentations.dashboard.payment.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solo.components.Elevation
import com.solo.components.R
import com.solo.components.theme.SoloDemoTheme

@Composable
fun PaymentWalletCard(
    onWalletClicked: () -> Unit,
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(10.dp)
            .clickable { onWalletClicked() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = Elevation.level5),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    text = stringResource(R.string.eats_wallet),
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.amount),
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontSize = 16.sp,
                    lineHeight = 14.sp,
                    textAlign = TextAlign.Start,
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
internal fun PaymentWalletCardPreview() {
    SoloDemoTheme(
        darkTheme = false,
    ) {
        Surface {
            PaymentWalletCard(onWalletClicked = {})
        }
    }
}
