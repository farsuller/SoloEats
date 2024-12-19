package com.solo.components

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp

val waterBrush = GoogleFont("Caveat Brush")
val roboto = GoogleFont("Roboto")

val fontFamilyRobotoExtraBold = FontFamily(
    Font(
        googleFont = roboto,
        fontProvider = provider,
        weight = FontWeight.ExtraBold,
        style = FontStyle.Normal,
    ),
)
val fontFamilyRobotoBold = FontFamily(
    Font(
        googleFont = roboto,
        fontProvider = provider,
        weight = FontWeight.Bold,
        style = FontStyle.Normal,
    ),
)
val fontFamilyRoboto = FontFamily(
    Font(
        googleFont = roboto,
        fontProvider = provider,
        weight = FontWeight.Normal,
        style = FontStyle.Normal,
    ),
)

val WaterBrush = FontFamily(
    androidx.compose.ui.text.font.Font(R.font.regular_brush),
)

// Set of Material typography styles to start with
val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = fontFamilyRobotoExtraBold,
        fontSize = 21.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = fontFamilyRobotoBold,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = fontFamilyRoboto,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp,
    ),

    labelSmall = TextStyle(
        fontFamily = fontFamilyRoboto,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = fontFamilyRoboto,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = fontFamilyRoboto,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 5.sp,
        letterSpacing = 0.5.sp,
    ),
)
