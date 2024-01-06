package com.solodemo.auth.presenations.forgot

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.solo.components.topbar.GenericBackTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun ForgotScreen(
    onButtonClicked : () -> Unit){
    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            GenericBackTopBar(onBackButton = { onButtonClicked() })
        },
       content = {
           ForgotContent(
               onSubmitClicked = onButtonClicked,
               paddingValues = it
           )
       }
    )

}
