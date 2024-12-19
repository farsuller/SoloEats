package com.solodemo.auth.presenations.login

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowInsetsController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import com.solodemo.auth.presenations.login.components.LoginBackground
import com.solodemo.auth.presenations.login.components.LoginBottomItems
import com.solodemo.auth.presenations.login.components.LoginHeader
import com.solodemo.auth.presenations.login.components.LoginTextFields

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun LoginScreen(
    onForgotButtonClicked: () -> Unit,
    onSignUpButtonClicked: () -> Unit,
    onLoggedInButtonClicked: (String, String) -> Unit,
) {
    val window = (LocalView.current.context as Activity).window

    DynamicStatusBar(
        window = window,
        statusBarColor = MaterialTheme.colorScheme.surface)

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
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
                        onLoggedInButtonClicked = onLoggedInButtonClicked,
                    )
                    LoginBottomItems(onSignUpButtonClicked = onSignUpButtonClicked)
                }
            }
        },
    )
}
@Composable
private fun DynamicStatusBar(
    window: Window,
    statusBarColor: Color) {
    LaunchedEffect(statusBarColor) {
        window.decorView.rootView.setBackgroundColor(statusBarColor.toArgb())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
            )
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }
    }
}