package com.solodemo.auth.presenations.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeveloperMode
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.solo.components.buttons.GoogleButton
import com.solo.util.getAppVersion
import com.solo.util.routes.ScreensRoutes
import com.solodemo.auth.presenations.AuthViewModel


@Composable
internal fun LoginContent(
    onGoogleButtonClicked: () -> Unit,
    onForgotButtonClicked: () -> Unit,
    onSignUpButtonClicked: () -> Unit,
    authViewModel : AuthViewModel

) {
    val focusManager = LocalFocusManager.current
    val appVersion = getAppVersion(LocalContext.current)
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isUsernameValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }

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
                OutlinedTextField(
                    value = username,
                    onValueChange = {
                        username = it
                        isUsernameValid = it.isNotEmpty()
                    },
                    label = { Text("Username or Email") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                    isError = !isUsernameValid,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        isPasswordValid = it.isNotEmpty()
                    },
                    label = { Text("Password") },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                    isError = !isPasswordValid,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            // Handle login button click or perform any other action
                            focusManager.clearFocus()
                        }
                    )
                )
                Button(
                    onClick = { authViewModel.signInEmail(email = username, password = password) },
                    enabled = isUsernameValid && isPasswordValid,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Login")
                }
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