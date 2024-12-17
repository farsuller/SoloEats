package com.solodemo.auth.presenations.login

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.solodemo.auth.presenations.AuthViewModel
import com.solodemo.auth.presenations.login.components.LoginBackground
import com.solodemo.auth.presenations.login.components.LoginBottomItems
import com.solodemo.auth.presenations.login.components.LoginHeader
import com.solodemo.auth.presenations.login.components.LoginTextFields

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun LoginScreen(
    onForgotButtonClicked: () -> Unit,
    onSignUpButtonClicked: () -> Unit,
    navigateToMain: () -> Unit,
) {
    val authViewModel = hiltViewModel<AuthViewModel>()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .navigationBarsPadding(),
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.onPrimary),
            ) {
                LoginBackground()

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(start = 40.dp, end = 40.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    LoginHeader()

                    LoginTextFields(
                        onForgotButtonClicked = onForgotButtonClicked,
                        onLoggedInButtonClicked = { email, password ->
                            authViewModel.login(email, password)
                            navigateToMain()
                        },
                    )

                    LoginBottomItems(onSignUpButtonClicked = onSignUpButtonClicked)
                }
            }
        },
    )
}
