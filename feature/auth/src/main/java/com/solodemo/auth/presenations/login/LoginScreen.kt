package com.solodemo.auth.presenations.login

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.solodemo.auth.presenations.AuthViewModel
import com.solodemo.auth.presenations.login.LoginContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun LoginScreen(
    onForgotButtonClicked: () -> Unit,
    onSignUpButtonClicked: () -> Unit,
    authViewModel : AuthViewModel){
    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
            .navigationBarsPadding(),
       content = {
           LoginContent(
               onForgotButtonClicked = onForgotButtonClicked,
               onSignUpButtonClicked = onSignUpButtonClicked,
               authViewModel = authViewModel)
       }
    )

}
