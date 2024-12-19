package com.solodemo.auth.presenations.signup

import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes
import com.solodemo.auth.presenations.AuthViewModel
import com.stevdzasan.messagebar.ContentWithMessageBar
import com.stevdzasan.messagebar.MessageBarPosition
import com.stevdzasan.messagebar.rememberMessageBarState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun NavGraphBuilder.signUpRoute(onBackPressClicked: () -> Unit) {
    composable(route = ScreensRoutes.SignUp.route) {
        val authViewModel = hiltViewModel<AuthViewModel>()
        val messageBarState = rememberMessageBarState()
        val scope = rememberCoroutineScope()

        ContentWithMessageBar(
            messageBarState = messageBarState,
            showCopyButton = false,
            position = MessageBarPosition.BOTTOM,
        ) {
            SignUpScreen(
                onBackPressClicked = onBackPressClicked,
                onSignUpButtonClicked = { email, password ->

                    authViewModel.signUp(
                        email = email,
                        password = password,
                        onSuccess = {
                            messageBarState.addSuccess("Successfully Signed Up")
                            scope.launch {
                                delay(600)
                                onBackPressClicked()
                            }
                        },
                        onError = { error ->
                            messageBarState.addError(error)
                        },
                    )
                },
            )
        }
    }
}
