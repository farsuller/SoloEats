package com.solodemo.main.presentations.dashboard.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.solo.components.Constants.StaticImages.DefaultImage
import com.solo.components.Elevation
import com.solo.components.R
import com.solo.components.clickableWithoutRipple
import com.solo.components.shapes.TicketShape
import com.solodemo.network.domain.model.Coupon

@Composable
fun CouponItemCard(coupon: Coupon, isSelected: Boolean, onItemClick: () -> Unit) {
    val lottieDelivery by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.delivery1))
    val progress by animateLottieCompositionAsState(
        composition = lottieDelivery,
        iterations = LottieConstants.IterateForever,
    )

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
            if (coupon.id == 1) {
                LottieAnimation(
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(60.dp)
                        .weight(1F),
                    composition = lottieDelivery,
                    progress = { progress },
                )
            } else {
                SubcomposeAsyncImage(
                    model = coupon.imagePath ?: DefaultImage,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(60.dp)
                        .padding(bottom = 10.dp)
                        .weight(1F),
                )
            }

            Column(
                modifier = Modifier
                    .padding(bottom = 10.dp, end = 5.dp)
                    .fillMaxWidth()
                    .weight(1F),
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${coupon.title}",
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    lineHeight = 14.sp,
                    textAlign = TextAlign.Start,
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${coupon.description}",
                    fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    lineHeight = 14.sp,
                    textAlign = TextAlign.Start,
                )
            }
        }
    }
}
