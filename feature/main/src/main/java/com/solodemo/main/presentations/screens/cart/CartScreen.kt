package com.solodemo.main.presentations.screens.cart

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun CartScreen(
    onButtonClicked: () -> Unit
) {
    CartContent(
        onButtonClicked = onButtonClicked
    )

}