package com.solodemo.main.screens.payment

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun PaymentScreen(
    onButtonClicked : () -> Unit){
    PaymentContent(
        onButtonClicked = onButtonClicked
    )

}
