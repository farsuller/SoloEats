package com.solodemo.auth.presenations.signup

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
import androidx.compose.foundation.layout.offset
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solo.components.Constants
import com.solo.components.R
import com.solo.ui.WaterBrush
import com.solo.components.component.ClickableBottomText
import com.solo.util.isValidEmail
import com.solodemo.auth.presenations.AuthViewModel
import com.solodemo.auth.presenations.login.components.LoginBackground
import com.solodemo.auth.presenations.login.components.LoginHeader
import com.solodemo.auth.presenations.signup.components.SignUpBackground
import com.solodemo.auth.presenations.signup.components.SignUpHeader

@Composable
internal fun SignUpContent(
    onHighlightTextClicked: () -> Unit,
    authViewModel: AuthViewModel
) {
    val focusManager = LocalFocusManager.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var isEmailValid by remember { mutableStateOf(false) }
    var isPasswordValid by remember { mutableStateOf(false) }
    var isConfirmPasswordValid by remember { mutableStateOf(false) }

    val isNotMatching = remember {
        derivedStateOf {
            password != confirmPassword
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.onPrimary)
    ) {

        SignUpBackground()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = 90.dp, y = (0).dp)
        ) {
            SignUpHeader(imageFile = Constants.StaticImages.food)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 40.dp, end = 40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(modifier = Modifier.weight(0.4F))

            Column(
                modifier = Modifier
                    .weight(2.5F)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(id = R.drawable.soloeats_logo),
                        contentDescription = "App Logo"
                    )

                    Text(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        text = "Sign Up",
                        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        color = MaterialTheme.colorScheme.secondary,
                    )
                }

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
                        errorBorderColor = MaterialTheme.colorScheme.secondary,
                        errorLabelColor = MaterialTheme.colorScheme.onSurface
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
                    ),
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
                        unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                        errorBorderColor = MaterialTheme.colorScheme.secondary,
                        errorLabelColor = MaterialTheme.colorScheme.onSurface
                    ),
                    isError = !isPasswordValid,
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.moveFocus(
                            FocusDirection.Down
                        )
                    })
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                        isConfirmPasswordValid = it.isNotEmpty()
                    },
                    label = { Text("Confirm Password") },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                        errorBorderColor = MaterialTheme.colorScheme.secondary,
                        errorLabelColor = MaterialTheme.colorScheme.onSurface
                    ),
                    isError = !isConfirmPasswordValid,
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                )


                Button(
                    onClick = {
                        authViewModel.setLoading(true)
                        authViewModel.signUpEmail(email = email, password = password,)
                    },
                    enabled = isEmailValid && isPasswordValid && isConfirmPasswordValid && !isNotMatching.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .padding(bottom = 10.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)

                ) {
                    if(authViewModel.loadingState.value) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = MaterialTheme.colorScheme.surface
                        )
                    }
                    else{
                        Text(
                            text = "Submit",
                            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            color = MaterialTheme.colorScheme.surface
                        )
                    }
                }

                ClickableBottomText(
                    onClick = { onHighlightTextClicked() },
                    appendText = "Already have Account? ",
                    appendHighlightText = "Login"
                )
            }

        }
        Box(
            modifier = Modifier
                .size(210.dp)
                .offset(x = (-30).dp, y = 700.dp)
        ) {
            SignUpHeader(
                borderColor = MaterialTheme.colorScheme.secondary,
                imageFile = Constants.StaticImages.fries,
                alignment = Alignment.Start
            )
        }
    }



}