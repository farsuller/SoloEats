package com.solodemo.main.presentations.dashboard.home.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.solo.ui.Elevation
import com.solo.components.clickableWithoutRipple
import com.solodemo.supabase.domain.model.Menu

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun HomeMenusCard(
    index: Int,
    menus: List<Menu>,
    onClick: () -> Unit,
) {
    ElevatedCard(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .size(100.dp)
            .clickableWithoutRipple(
                onClick = { onClick() },
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = Elevation.level4),
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
    ) {
        Box(modifier = Modifier) {
            SubcomposeAsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = menus[index].menuImage,
                contentScale = ContentScale.Crop,
                contentDescription = menus[index].menuName,
                alignment = Alignment.TopCenter,
            )

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f)),
            ) {
                Text(
                    text = "${menus[index].menuName}",
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = MaterialTheme.colorScheme.surface,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
