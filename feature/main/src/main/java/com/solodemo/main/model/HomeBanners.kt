package com.solodemo.main.model

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import com.solo.components.Constants
import com.solo.ui.md_theme_light_onTertiary
import com.solo.ui.md_theme_light_primary
import com.solo.ui.md_theme_light_secondary


enum class HomeBanners(
    val imagePath: String,
    val color: Color,
    val title: String,
    val isInverted: Boolean = false
) {
    Burger(
        imagePath = Constants.StaticImages.bannerBurger,
        color = md_theme_light_primary,
        title = "Bite into Boldness"
    ),
    Pizza(
        imagePath = Constants.StaticImages.bannerBurger,
        color = md_theme_light_onTertiary,
        title = "Slice into Perfection",
        isInverted = true
    ),
    Coffee(
        imagePath = Constants.StaticImages.bannerBurger,
        color = md_theme_light_secondary,
        title = "Awaken Your Senses"
    )
}