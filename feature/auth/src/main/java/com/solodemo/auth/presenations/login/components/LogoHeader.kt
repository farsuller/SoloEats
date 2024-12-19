package com.solodemo.auth.presenations.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solo.components.R
import com.solo.components.WaterBrush

@Composable
fun AppIconAndName() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
                .background(MaterialTheme.colorScheme.secondary),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(30.dp),
                painter = painterResource(id = R.drawable.soloeats_logo),
                contentDescription = "App Logo",
            )
        }

        Text(
            modifier = Modifier.padding(horizontal = 10.dp),
            text = "SoloEats",
            fontFamily = WaterBrush,
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}
