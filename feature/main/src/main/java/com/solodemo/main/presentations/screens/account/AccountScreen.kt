package com.solodemo.main.presentations.screens.account

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun AccountScreen(
    onButtonClicked: () -> Unit,
    paddingValues: PaddingValues

) {
    AccountContent(
        paddingValues = paddingValues,
        onButtonClicked = onButtonClicked
    )

}
