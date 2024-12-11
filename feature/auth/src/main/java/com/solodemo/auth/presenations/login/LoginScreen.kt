package com.solodemo.auth.presenations.login

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.solodemo.auth.presenations.AuthViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun LoginScreen(
    onForgotButtonClicked: () -> Unit,
    onSignUpButtonClicked: () -> Unit,
    authViewModel: AuthViewModel,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .navigationBarsPadding(),
        content = {
            LoginContent(
                onForgotButtonClicked = onForgotButtonClicked,
                onSignUpButtonClicked = onSignUpButtonClicked,
                authViewModel = authViewModel,
            )
        },
    )
}
