package com.solo.components.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CircleImageItem(
    imageFile: String,
    borderColor: Color
) {

    Box(
        modifier = Modifier
            .size(300.dp)
            .border(width = 7.dp, color = borderColor, shape = CircleShape)
            .padding(1.dp)
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ){

            AsyncImage(
                modifier = Modifier
                    .size(300.dp)
                    .clip(shape = CircleShape),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageFile)
                    .crossfade(true).build(),
                contentDescription = "Logo",
                contentScale = ContentScale.Crop)


        }

}