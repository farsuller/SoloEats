package com.solo.components.component


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BoxWithSmallCircles(modifier: Modifier = Modifier, circleSize: Dp = 5.dp, circleColor:Color, circleCount: Int = 4, circleCount2 :Int = 3) {
    Box(modifier = modifier) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                repeat(circleCount){
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .size(circleSize)
                            .clip(CircleShape)
                            .background(color = circleColor)
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(circleCount2){
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .size(circleSize)
                            .clip(CircleShape)
                            .background(color = circleColor)
                    )
                }

            }
        }
    }
}