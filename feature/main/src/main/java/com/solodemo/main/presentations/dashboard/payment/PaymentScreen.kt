package com.solodemo.main.presentations.dashboard.payment

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun PaymentScreen(
    paddingValues: PaddingValues
) {
    PaymentContent(paddingValues = paddingValues)

}
