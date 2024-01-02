package com.solodemo.main.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.solo.ui.Elevation
import com.solo.util.clickableWithoutRipple
import com.solodemo.supabase.model.Menu


@Composable
fun HomeMenusCard(index : Int, menus: List<Menu>, onClick :()-> Unit) {

    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = Elevation.level4),
        modifier = Modifier.padding(horizontal = 5.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 20.dp)
                .clickableWithoutRipple(
                    interactionSource = MutableInteractionSource(),
                    onClick = { onClick() }
                )
        )
        {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(100.dp),
                model = menus[index].menuImage,
                contentScale = ContentScale.Fit,
                contentDescription = menus[index].menuName,
                alignment = Alignment.TopCenter
            )

            Text(
                text = menus[index].menuName ?: "Default",
                textAlign = TextAlign.Center,
                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }

    }


}