package com.solodemo.main.screens.account

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun AccountScreen(
    onButtonClicked : () -> Unit){
    AccountContent(
        onButtonClicked = onButtonClicked
    )

}
