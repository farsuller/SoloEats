package com.solodemo.main.presentations.dashboard.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solo.components.formatToCurrency
import com.solodemo.main.presentations.dashboard.cart.CartState

@Composable
fun SubtotalDeliveryContent(cartState: CartState) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f)),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "SubTotal",
                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
            )

            Text(
                text = formatToCurrency(cartState.subTotalPrice),
                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Delivery fee",
                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
            )
            Text(
                text = formatToCurrency(cartState.deliveryFee),
                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
            )
        }
    }
}
