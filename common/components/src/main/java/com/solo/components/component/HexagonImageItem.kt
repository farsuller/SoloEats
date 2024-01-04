package com.solo.components.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.solo.components.Constants
import com.solo.components.shapes.HexagonShape
import com.solo.components.shapes.drawCustomHexagonPath

@Composable
fun HexagonImageItem(modifier: Modifier = Modifier, hexagonSize : Dp = 200.dp, imageFile :String, borderColor: Color) {

    Box(modifier = modifier
        .rotate(30f)
        .drawWithContent {
            drawContent()
            drawPath(
                path = drawCustomHexagonPath(size),
                color = borderColor,
                style = Stroke(
                    width = 7.dp.toPx(),
                    pathEffect = PathEffect.cornerPathEffect(40f)
                )
            )
        }
        .wrapContentSize()
    ){
        Box(modifier = Modifier
            .size(hexagonSize)
            .graphicsLayer {
                shape = HexagonShape()
                clip = true
            }
        ) {

            AsyncImage(modifier = Modifier
                .rotate(-30f),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageFile)
                    .crossfade(true).build(),
                contentDescription = "Logo",
                contentScale = ContentScale.Crop)


        }
    }

}