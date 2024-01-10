package com.solodemo.main.screens.payment

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun PaymentScreen(
    onWalletClicked : () -> Unit,
    paddingValues: PaddingValues){
    PaymentContent(
        onWalletClicked = onWalletClicked,
        paddingValues = paddingValues
    )

}
