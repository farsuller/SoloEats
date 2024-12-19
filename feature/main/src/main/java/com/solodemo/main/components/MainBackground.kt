package com.solodemo.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solo.components.R
import com.solo.components.component.BoxWithSmallCircles
import com.solo.components.theme.SoloDemoTheme

@Composable
fun MainBackground() {
    Box(modifier = Modifier.fillMaxSize()) {
        BoxWithSmallCircles(
            modifier = Modifier
                .size(60.dp)
                .offset(x = 90.dp, y = 90.dp),
            circleSize = 5.dp,
            circleColor = MaterialTheme.colorScheme.primary,
        )

        BoxWithSmallCircles(
            modifier = Modifier
                .size(60.dp)
                .offset(x = 20.dp, y = 670.dp),
            circleSize = 7.dp,
            circleColor = MaterialTheme.colorScheme.primary,
            circleCount = 2,
            circleCount2 = 3,
        )

        BoxWithSmallCircles(
            modifier = Modifier
                .size(60.dp)
                .offset(x = 320.dp, y = 470.dp),
            circleSize = 7.dp,
            circleColor = MaterialTheme.colorScheme.primary,
            circleCount = 1,
            circleCount2 = 2,
        )
        BoxWithSmallCircles(
            modifier = Modifier
                .size(60.dp)
                .offset(x = 320.dp, y = 670.dp),
            circleSize = 9.dp,
            circleColor = MaterialTheme.colorScheme.secondary,
            circleCount = 1,
            circleCount2 = 0,
        )
        BoxWithSmallCircles(
            modifier = Modifier
                .size(60.dp)
                .offset(x = 120.dp, y = 290.dp),
            circleSize = 9.dp,
            circleColor = MaterialTheme.colorScheme.secondary,
            circleCount = 2,
            circleCount2 = 0,
        )

        // Right Hexagon Header
        Icon(
            modifier = Modifier
                .size(70.dp)
                .rotate(15F)
                .offset(x = 430.dp, y = 150.dp),
            painter = painterResource(id = R.drawable.square_maze),
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = null,
        )

        Icon(
            modifier = Modifier
                .size(70.dp)
                .rotate(0F)
                .offset(x = (-40).dp, y = 200.dp),
            painter = painterResource(id = R.drawable.hexagonal_maze),
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = null,
        )

        Icon(
            modifier = Modifier
                .size(70.dp)
                .offset(x = 180.dp, y = 160.dp),
            painter = painterResource(id = R.drawable.zigzag_line),
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = null,
        )

        Icon(
            modifier = Modifier
                .size(70.dp)
                .offset(x = 100.dp, y = 400.dp),
            painter = painterResource(id = R.drawable.zigzag_line),
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = null,
        )

        Box(
            modifier = Modifier
                .size(200.dp)
                .rotate(30F)
                .offset(x = 600.dp, y = 600.dp)
                .clip(RoundedCornerShape(15))
                .background(MaterialTheme.colorScheme.onTertiary),
        )

        BoxWithSmallCircles(
            modifier = Modifier
                .size(60.dp)
                .offset(x = 260.dp, y = 815.dp),
            circleSize = 6.dp,
            circleColor = MaterialTheme.colorScheme.secondary,
            circleCount = 2,
            circleCount2 = 1,
        )

        Icon(
            modifier = Modifier
                .size(100.dp)
                .offset(x = 245.dp, y = 805.dp),
            painter = painterResource(id = R.drawable.zigzag_line),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null,
        )

        // Bottom Right Corner
        Box(modifier = Modifier.offset(x = 400.dp, y = 750.dp)) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .rotate(30F)
                    .offset(x = (-85).dp, y = 50.dp)
                    .clip(RoundedCornerShape(15))
                    .background(MaterialTheme.colorScheme.onTertiary),
            )

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .rotate(15F)
                    .offset(x = (-75).dp, y = 35.dp)
                    .clip(RoundedCornerShape(15))
                    .background(MaterialTheme.colorScheme.secondary),
            )

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .offset(x = (-30).dp, y = 0.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainBackGroundPreview() {
    SoloDemoTheme(
        dynamicColor = false,
    ) {
        Surface {
            MainBackground()
        }
    }
}
