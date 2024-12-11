package com.solo.components.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.solo.components.shapes.HexagonShape

@Composable
fun HexagonBackGroundItem(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(200.dp)
            .rotate(30f),
    ) {
        Column(
            modifier = Modifier
                .rotate(-30f)
                .fillMaxSize()
                .graphicsLayer {
                    shape = HexagonShape()
                    clip = true
                    rotationZ = 30f
                }
                .background(color = MaterialTheme.colorScheme.primary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .rotate(-30f)
                    .height(200.dp),

            )
        }
    }
}
