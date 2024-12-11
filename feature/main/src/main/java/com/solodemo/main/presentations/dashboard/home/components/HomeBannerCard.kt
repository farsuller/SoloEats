package com.solodemo.main.presentations.dashboard.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.solo.components.Elevation

@Composable
fun HomeBannerCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String = "",
    color: Color,
    imagePath: String,
    isInverted: Boolean = false,
) {
    ElevatedCard(
        modifier = modifier.size(width = 300.dp, height = 160.dp),
        shape = RoundedCornerShape(
            topStart = 42.dp,
            topEnd = 6.dp,
            bottomStart = 6.dp,
            bottomEnd = 42.dp,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = Elevation.level5),
        colors = CardDefaults.cardColors(containerColor = color),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            if (isInverted) {
                BannerImage(
                    modifier = Modifier
                        .weight(0.5F)
                        .fillMaxSize(),
                    imagePath = imagePath,
                )
                BannerText(
                    modifier = Modifier
                        .weight(0.5F)
                        .fillMaxSize()
                        .padding(10.dp),
                    title = title,
                    description = description,
                )
            } else {
                BannerText(
                    modifier = Modifier
                        .weight(0.5F)
                        .fillMaxSize()
                        .padding(10.dp),
                    title = title,
                    description = description,
                    alignment = Alignment.Start,
                    textAlign = TextAlign.Start,
                )

                BannerImage(
                    modifier = Modifier
                        .weight(0.5F)
                        .fillMaxSize(),
                    imagePath = imagePath,
                )
            }
        }
    }
}

@Composable
fun BannerImage(modifier: Modifier, imagePath: String) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = imagePath,
        contentDescription = "Banner Image",
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center,
    )
}

@Composable
fun BannerText(
    modifier: Modifier,
    title: String,
    description: String,
    alignment: Alignment.Horizontal = Alignment.End,
    textAlign: TextAlign = TextAlign.End,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = alignment,
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            textAlign = textAlign,
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = description,
            fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            lineHeight = 14.sp,
            textAlign = textAlign,
        )
    }
}

@Preview(showBackground = true)
@Composable
internal fun HomeBannerCardPreview() {
    HomeBannerCard(
        title = "Title",
        description = "Indulge in the perfect harmony of flavors with our artisanal pizzas.",
        color = MaterialTheme.colorScheme.primary,
        imagePath = "",
    )
}
