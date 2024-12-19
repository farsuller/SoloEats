package com.solodemo.auth.presenations.forgot

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.solo.components.Constants
import com.solo.components.R
import com.solo.components.component.GenericBackTopBar
import com.solo.components.isValidEmail
import com.solodemo.auth.presenations.forgot.components.BottomHeader
import com.solodemo.auth.presenations.forgot.components.ForgotPasswordBackground

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun ForgotScreen(
    onButtonClicked: () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            GenericBackTopBar(onBackButton = { onButtonClicked() })
        },
        content = {
            val focusManager = LocalFocusManager.current
            var email by remember { mutableStateOf("") }
            var isEmailValid by remember { mutableStateOf(true) }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.onPrimary),
            ) {
                ForgotPasswordBackground()

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = it.calculateTopPadding() + 20.dp,
                            start = 20.dp,
                            end = 20.dp,
                        ),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Image(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.soloeats_logo),
                            contentDescription = "App Logo",
                        )

                        Text(
                            modifier = Modifier.padding(horizontal = 10.dp),
                            text = "Forgot Password?",
                            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = "No worries! Reset your password in seconds.\nEnter your email, and we'll guide you back into your account.",
                        fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = MaterialTheme.colorScheme.secondary,
                    )

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
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
                                imeAction = ImeAction.Next,
                            ),
                            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                        )

                        Button(
                            onClick = onButtonClicked,
                            enabled = isEmailValid,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                                .padding(bottom = 10.dp),
                            shape = RoundedCornerShape(5.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),

                        ) {
                            Text(
                                text = "Submit",
                                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                color = MaterialTheme.colorScheme.surface,
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .rotate(10F)
                        .offset(x = 140.dp, y = 550.dp),
                ) {
                    BottomHeader(
                        borderColor = MaterialTheme.colorScheme.primary,
                        imageFile = Constants.StaticImages.food2,
                        alignment = Alignment.Start,
                    )
                }
            }
        },
    )
}
