package com.solo.solodemo.presentations.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.solo.solodemo.model.Menus
import com.solo.solodemo.utils.clickableWithoutRipple
import com.solodemo.ui.theme.Elevation


@Composable
fun HomeMenusCard(menus: Menus) {

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = Elevation.level4
        ),
        modifier = Modifier.padding(horizontal = 5.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {

        HomeMenusCardItem(menus = menus, onClick = {})

    }


}

@Composable
fun HomeMenusCardItem(menus: Menus, onClick: () -> Unit) {
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
            model = menus.menuImage,
            contentScale = ContentScale.Fit,
            contentDescription = menus.text,
            alignment = Alignment.TopCenter
        )

        Text(
            text = menus.text,
            textAlign = TextAlign.Center,
            fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            color = MaterialTheme.colorScheme.onSurface,
        )

    }
}