package com.solodemo.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.solo.ui.Elevation
import com.solodemo.main.model.Featured

@Composable
fun CardProduct(
    modifier: Modifier = Modifier,
    title: String = "",
    price: String = "",
    imagePath: String = "",
    onAddButtonClicked: () -> Unit
) {
    ElevatedCard(
        modifier = modifier.size(width = 270.dp, height = 140.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = Elevation.level5),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            AsyncImage(
                modifier = Modifier.weight(0.3F),
                model = imagePath,
                contentDescription = "Banner Image",
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center
            )

            Column(
                modifier = Modifier
                    .weight(0.5F)
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
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start,
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = price,
                        fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                        fontSize = 16.sp,
                        lineHeight = 14.sp,
                        textAlign = TextAlign.Start,
                    )
                }

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(color = Color(0xFFF44336))
                ) {
                    IconButton(
                        onClick = { onAddButtonClicked() },
                        modifier = Modifier

                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Button",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = false)
@Composable
internal fun CardProductPreview() {
    CardProduct(
        imagePath = Featured.CheesyHavenDeluxe.imagePath,
        title = Featured.CheesyHavenDeluxe.title,
        price = Featured.CheesyHavenDeluxe.price,
        onAddButtonClicked = {}

    )
}