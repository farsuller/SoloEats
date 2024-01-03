package com.solodemo.main.screens.account

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.solodemo.main.MainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun AccountScreen(
    onButtonClicked : () -> Unit,
    paddingValues : PaddingValues

){
    AccountContent(
        paddingValues = paddingValues,
        onButtonClicked = onButtonClicked
    )

}
