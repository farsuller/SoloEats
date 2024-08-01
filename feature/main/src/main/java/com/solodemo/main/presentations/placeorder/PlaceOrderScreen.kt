package com.solodemo.main.presentations.placeorder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PlaceOrderScreen(
    onNavigateToMain: () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {},
        content = { paddingValues ->

            PlaceOrderContent(
                paddingValues = paddingValues,
                onNavigateToMain = onNavigateToMain,
            )
        },
    )
}
