package com.solodemo.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.solo.components.shapes.drawCustomHexagonPath
import com.solo.ui.Elevation
import com.solo.util.clickableWithoutRipple
import com.solodemo.supabase.model.Menu
import com.solodemo.supabase.model.Reel


@Composable
fun HomeReelsCard(index : Int,
                  reels: List<Reel>,
                  onClick :()-> Unit) {

    ElevatedCard(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .size(height = 200.dp, width = 120.dp)
            .clickableWithoutRipple(
            interactionSource = MutableInteractionSource(),
            onClick = { onClick() }
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = Elevation.level5),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {

        Box(modifier = Modifier)
        {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = reels[index].foodImage,
                contentScale = ContentScale.FillBounds,
                contentDescription = reels[index].foodImage,
                alignment = Alignment.TopCenter
            )

            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, bottom = 10.dp) ){
                Text(
                    text = "${reels[index].name}",
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = MaterialTheme.colorScheme.surface,
                    textAlign = TextAlign.Center,
                )
            }
        }

    }
}