package com.solodemo.home.presentations.dashboard.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solodemo.home.model.HomeBanners

@Composable
fun HomeBannersContent() {
    LazyRow(modifier = Modifier.padding(top = 10.dp, bottom = 15.dp)) {
        this.items(HomeBanners.entries.toTypedArray()) { entries ->
            HomeBannerCard(
                Modifier.padding(horizontal = 5.dp),
                title = entries.title,
                color = entries.color,
                imagePath = entries.imagePath,
            )
        }
    }
}
