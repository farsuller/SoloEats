package com.solodemo.main.presentations.dashboard.cart.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solo.components.Elevation

@Composable
fun PaymentMethodContent() {
    ElevatedCard(
        modifier = Modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = Elevation.level2),
        shape = RoundedCornerShape(13.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
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
        }
    }
}
