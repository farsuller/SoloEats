package com.solo.solodemo.presentations.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeveloperMode
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solo.solodemo.components.GoogleButton


@Composable
internal fun LoginContent(
    onGoogleButtonClicked: () -> Unit,
    onForgotButtonClicked: () -> Unit,
    onSignUpButtonClicked: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .weight(9f)
                .fillMaxWidth()
                .padding(all = 40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.weight(weight = 10f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(180.dp),
                    imageVector = Icons.Default.DeveloperMode,
                    contentDescription = "SoloScape Logo"
                )

                Text(
                    text = "Login",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )
                Text(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                    text = "Screen",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                )
            }
            Column(
                modifier = Modifier.weight(weight = 2f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GoogleButton(
                    onClick = onGoogleButtonClicked
                )
                Button(
                    onClick = onSignUpButtonClicked
                ){
                    Text(text = "Sign Up", color = MaterialTheme.colorScheme.onSurface)
                }
                Button(
                    onClick = onForgotButtonClicked
                ){
                    Text(text = "Forgot Password", color = MaterialTheme.colorScheme.onSurface)
                }
            }
        }
    }
}