package com.solodemo.auth.presenations.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solo.components.R
import com.solo.components.buttons.GoogleButton
import com.solo.ui.WaterBrush
import com.solo.util.clickableWithoutRipple
import com.solodemo.auth.presenations.AuthViewModel
import com.solo.components.component.ClickableBottomText
import com.solo.util.isValidEmail
import com.solodemo.auth.presenations.login.components.LoginBackground
import com.solodemo.auth.presenations.login.components.LoginHeader
import io.github.jan.supabase.compose.auth.composable.NativeSignInState
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth

@Composable
internal fun LoginContent(
    onForgotButtonClicked: () -> Unit,
    onSignUpButtonClicked: () -> Unit,
    authViewModel: AuthViewModel,
) {

    val googleSignIn = authViewModel.composeAuth.rememberSignInWithGoogle(
        onResult = { result -> authViewModel.checkGoogleLoginStatus(result) },
        fallback = {}
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.onPrimary)
    ) {

        LoginBackground()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 40.dp, end = 40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LoginHeader()

            LoginTextFields(
                onForgotButtonClicked = { onForgotButtonClicked() },
                authViewModel = authViewModel)

            LoginBottomItems(
                authViewModel = authViewModel,
                googleSignIn = googleSignIn,
                onSignUpButtonClicked = onSignUpButtonClicked
            )
        }
    }
}


@Composable
private fun AppIconAndName() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = R.drawable.soloeats_logo),
            contentDescription = "App Logo"
        )

        Text(
            modifier = Modifier.padding(horizontal = 10.dp),
            text = "SoloEats",
            fontFamily = WaterBrush,
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Composable
private fun LoginTextFields(
    onForgotButtonClicked: () -> Unit,
    authViewModel: AuthViewModel
) {
    val focusManager = LocalFocusManager.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AppIconAndName()

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            value = email,
            onValueChange = {
                email = it
                isEmailValid = isValidEmail(it)
            },
            label = { Text("Email") },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
            ),
            isError = !isEmailValid,
            singleLine = true,
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
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = {
                password = it
                isPasswordValid = it.isNotEmpty()
            },
            label = { Text("Password") },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
            ),
            isError = !isPasswordValid,
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                modifier = Modifier
                    .clickableWithoutRipple(
                        interactionSource = MutableInteractionSource(),
                        onClick = { onForgotButtonClicked() }),
                text = "Forgot Password?",
                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.End
            )
        }


        Button(
            onClick = {
                authViewModel.setLoginClicked(true)
                authViewModel.setLoading(true)
                authViewModel.signInEmail(email = email, password = password)
            },
            enabled = isEmailValid && isPasswordValid,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        )
        {
            if (authViewModel.loadingState.value) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = MaterialTheme.colorScheme.surface
                )
            } else {
                Text(
                    text = "Login",
                    fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    color = MaterialTheme.colorScheme.surface
                )
            }
        }
    }
}


@Composable
private fun LoginBottomItems(
    authViewModel: AuthViewModel,
    googleSignIn: NativeSignInState,
    onSignUpButtonClicked: () -> Unit
) {
    Text(
        text = "OR",
        fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
        color = MaterialTheme.colorScheme.onSurface
    )

    GoogleButton(
        modifier = Modifier.padding(top = 10.dp),
        loadingState = authViewModel.composeAuthState.value,
        onClick = {
            authViewModel.setGoogleClicked(true)
            authViewModel.setComposeAuthLoading(true)
            googleSignIn.startFlow()
        }
    )

    ClickableBottomText(
        onClick = { onSignUpButtonClicked() },
        appendText = "Haven't Account? then ",
        appendHighlightText = "Register Now"
    )
}
