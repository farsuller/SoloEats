package com.solodemo.home.presentations.dashboard.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.solo.components.Elevation
import com.solo.components.R
import com.solo.components.clickableWithoutRipple
import com.solo.components.shapes.TicketShape
import com.solodemo.network.domain.model.Coupon

@Composable
fun CouponItemCard(coupon: Coupon, isSelected: Boolean, onItemClick: () -> Unit) {
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
                .fillMaxWidth()
                .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (coupon.imagePath != null) {
                SubcomposeAsyncImage(
                    model = coupon.imagePath,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(40.dp)
                        .padding(10.dp)
                        .weight(1F),
                )
            } else {
                Icon(
                    modifier = Modifier
                        .weight(1F)
                        .size(50.dp),
                    painter = painterResource(id = R.drawable.ic_coupon),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                )
            }

            Column(
                modifier = Modifier
                    .padding(bottom = 10.dp, end = 5.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(1F),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${coupon.title}",
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    lineHeight = 14.sp,
                    textAlign = TextAlign.Start,
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${coupon.description}",
                    fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    lineHeight = 14.sp,
                    textAlign = TextAlign.Start,
                )
            }
        }
    }
}
