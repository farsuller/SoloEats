package com.solodemo.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.solo.components.Elevation

@Composable
fun MainHeaderCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String = "",
    color: Color,
    imagePath: String,
) {
    ElevatedCard(
        modifier = modifier,
        shape = RoundedCornerShape(0.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = Elevation.level0),
        colors = CardDefaults.cardColors(containerColor = color),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Column(
                modifier = Modifier
                    .weight(0.5F)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    lineHeight = 19.sp,
                    textAlign = TextAlign.Start,
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = description,
                    fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    lineHeight = 19.sp,
                    textAlign = TextAlign.Start,
                )
            }

            SubcomposeAsyncImage(
                modifier = Modifier
                    .weight(0.4F)
                    .fillMaxSize(),
                model = imagePath,
                contentDescription = "Banner Image",
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun HomeHeaderCardPreview() {
    MainHeaderCard(
        title = "Title",
        description = "Indulge in the perfect harmony of flavors with our artisanal pizzas.",
        color = MaterialTheme.colorScheme.primary,
        imagePath = "",
    )
}
