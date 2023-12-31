package com.solo.solodemo.presentations.main.screens.payment

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import com.solo.solodemo.components.GenericBackTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun PaymentScreen(
    onButtonClicked : () -> Unit){
    PaymentContent(
        onButtonClicked = onButtonClicked
    )

}
