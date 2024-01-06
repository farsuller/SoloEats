package com.solodemo.auth.presenations.forgot.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.solo.components.R
import com.solo.components.component.BoxWithSmallCircles

@Composable
fun ForgotPasswordBackground() {


//Top Left Corner
//    Box(modifier = Modifier.offset(x = (-80).dp, y = 100.dp)) {
//
//        Box(
//            modifier = Modifier
//                .size(170.dp)
//                .offset(x = 0.dp, y = 0.dp)
//                .clip(CircleShape)
//                .background(MaterialTheme.colorScheme.onTertiary)
//        )
//        Box(
//            modifier = Modifier
//                .size(120.dp)
//                .rotate(15F)
//                .offset(x = 0.dp, y = 0.dp)
//                .clip(RoundedCornerShape(15))
//                .background(MaterialTheme.colorScheme.secondary)
//        ) {
//            Box(
//                modifier = Modifier
//                    .offset(x = 25.dp, y = 25.dp)
//                    .paint(painter = painterResource(id = R.drawable.zigzag_line)),
//
//                )
//        }
//    }






    //Left Hexagon Header
    BoxWithSmallCircles(
        modifier = Modifier
            .size(60.dp)
            .offset(x = 20.dp, y = 670.dp),
        circleSize = 7.dp,
        circleColor = MaterialTheme.colorScheme.primary,
        circleCount = 2,
        circleCount2 = 3
    )


    Icon(
        modifier = Modifier
            .size(70.dp)
            .offset(x = 140.dp, y = 360.dp),
        painter = painterResource(id = R.drawable.zigzag_line),
        tint = MaterialTheme.colorScheme.secondary,
        contentDescription = null
    )

    Icon(
        modifier = Modifier
            .size(70.dp)
            .offset(x = 25.dp, y = 260.dp),
        painter = painterResource(id = R.drawable.zigzag_line),
        tint = MaterialTheme.colorScheme.primary,
        contentDescription = null
    )



    //Bottom Right Corner
    Box(
        modifier = Modifier
            .size(200.dp)
            .rotate(30F)
            .offset(x = 600.dp, y = 600.dp)
            .clip(RoundedCornerShape(15))
            .background(MaterialTheme.colorScheme.onTertiary)
    )

    BoxWithSmallCircles(
        modifier = Modifier
            .size(60.dp)
            .offset(x = 260.dp, y = 815.dp),
        circleSize = 6.dp,
        circleColor = MaterialTheme.colorScheme.secondary,
        circleCount = 2,
        circleCount2 = 1
    )

    Icon(
        modifier = Modifier
            .size(100.dp)
            .offset(x = 245.dp, y = 805.dp),
        painter = painterResource(id = R.drawable.zigzag_line),
        tint = MaterialTheme.colorScheme.primary,
        contentDescription = null
    )

    //Bottom Right Corner
    Box(modifier = Modifier.offset(x = 400.dp, y = 50.dp)) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .rotate(30F)
                .offset(x = (-125).dp, y = 50.dp)
                .clip(RoundedCornerShape(15))
                .background(MaterialTheme.colorScheme.onTertiary)
        )

        Box(
            modifier = Modifier
                .size(120.dp)
                .rotate(15F)
                .offset(x = (-75).dp, y = (-5).dp)
                .clip(RoundedCornerShape(15))
                .background(MaterialTheme.colorScheme.secondary)
        )

        Box(
            modifier = Modifier
                .size(100.dp)
                .offset(x = (-30).dp, y = 0.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
        )
    }

    //Top Right Corner
    BoxWithSmallCircles(
        modifier = Modifier
            .size(60.dp)
            .offset(x = 280.dp, y = 60.dp),
        circleSize = 7.dp,
        circleColor = MaterialTheme.colorScheme.secondary,
        circleCount = 2,
        circleCount2 = 1
    )

}
