package com.solo.solodemo.presentations.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.solo.solodemo.components.HexagonShape
import com.solo.solodemo.model.Menus

@Composable
fun HexagonImageTextItem(index: Int, modifier: Modifier = Modifier) {
    val menuList = Menus.entries.toTypedArray()
    Box(
        modifier = modifier
            .size(200.dp)
            .rotate(30f),
    ) {
        Column(modifier = Modifier
            .rotate(-30f)
            .fillMaxSize()
            .graphicsLayer {
                shadowElevation = 8.dp.toPx()
                shape = HexagonShape()
                clip = true
                rotationZ = 30f
            }
            .background(color = MaterialTheme.colorScheme.primary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            AsyncImage(
                modifier = Modifier
                    .rotate(-30f)
                    .offset(x = (-5).dp)
                    .height(100.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(menuList[index].menuImage)
                    .crossfade(true).build(),
                contentDescription = "Logo",
                contentScale = ContentScale.Fit
            )

            Text(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .rotate(-30f)
                    .offset(x = 25.dp),
                text = menuList[index].text,
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
            )
        }

    }
}