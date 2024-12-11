package com.solodemo.main.presentations.dashboard.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.solo.components.Elevation
import com.solo.components.clickableWithoutRipple
import com.solo.components.shapes.TicketShape
import com.solodemo.main.model.Coupons

@Composable
fun CouponItemCard(coupons: Coupons, isSelected: Boolean, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(width = 230.dp, height = 110.dp)
            .padding(8.dp)
            .clickableWithoutRipple(
                onClick = { onItemClick.invoke() },
            ),
        shape = TicketShape(),
        elevation = CardDefaults.cardElevation(defaultElevation = Elevation.level2),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (coupons.imagePath.isNotEmpty()) {
                SubcomposeAsyncImage(
                    model = coupons.imagePath,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                )
            }

            Column(modifier = Modifier.padding(bottom = 10.dp, start = 5.dp, end = 5.dp)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = coupons.title,
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    lineHeight = 14.sp,
                    textAlign = TextAlign.Center,
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = coupons.description,
                    fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    lineHeight = 14.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
