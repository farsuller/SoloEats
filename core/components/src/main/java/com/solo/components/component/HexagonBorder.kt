package com.solo.components.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.solo.components.shapes.HexagonShape
import com.solo.components.shapes.drawCustomHexagonPath

@Composable
fun HexagonBorder(
    modifier: Modifier = Modifier,
    hexagonStroke: Dp = 5.dp,
    hexagonSize: Dp = 130.dp,
    color: Color,
) {
    Box(
        modifier = modifier
            .drawWithContent {
                drawContent()
                drawPath(
                    path = drawCustomHexagonPath(size),
                    color = color,
                    style = Stroke(
                        width = hexagonStroke.toPx(),
                        pathEffect = PathEffect.cornerPathEffect(40f),
                    ),
                )
            }
            .wrapContentSize(),
    ) {
        Box(
            modifier = Modifier
                .size(hexagonSize)
                .graphicsLayer {
                    shape = HexagonShape()
                    clip = true
                },
        )
    }
}
