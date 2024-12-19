package com.solodemo.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.solo.components.R

@Composable
fun RatingBar(modifier: Modifier = Modifier, starsCount: Int? = 5) {
    Row(modifier = modifier) {
        if (starsCount != null) {
            repeat(starsCount) {
                Image(
                    modifier = Modifier
                        .padding(2.dp)
                        .size(14.dp),
                    painter = painterResource(id = R.drawable.star_icon),
                    contentDescription = "Star Icon",
                )
            }
        }
    }
}
