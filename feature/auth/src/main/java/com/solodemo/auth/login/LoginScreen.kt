package com.solodemo.auth.login

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.solodemo.auth.login.LoginContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun LoginScreen(
    onGoogleButtonClicked : () -> Unit,
    onForgotButtonClicked: () -> Unit,
    onSignUpButtonClicked: () -> Unit){
    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
            .navigationBarsPadding(),
       content = {
           LoginContent(
               onGoogleButtonClicked = onGoogleButtonClicked,
               onForgotButtonClicked = onForgotButtonClicked,
               onSignUpButtonClicked = onSignUpButtonClicked
           )
       }
    )

}