package com.solodemo.auth.presenations.login

import androidx.compose.runtime.LaunchedEffect
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

fun NavGraphBuilder.loginRoute(
    navigateToMain: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToForgot: () -> Unit,
    onDataLoaded: (Boolean) -> Unit,
) {
    composable(route = ScreensRoutes.Auth.route) {
        val authViewModel = hiltViewModel<AuthViewModel>()
        val messageBarState = rememberMessageBarState()
        val scope = rememberCoroutineScope()

        LaunchedEffect(key1 = Unit) {
            onDataLoaded(false)
        }
        ContentWithMessageBar(
            messageBarState = messageBarState,
            showCopyButton = false,
            position = MessageBarPosition.TOP,
        ) {
            LoginScreen(
                onForgotButtonClicked = { navigateToForgot() },
                onSignUpButtonClicked = { navigateToSignUp() },
                onLoggedInButtonClicked = { email, password ->
                    authViewModel.login(
                        email = email,
                        password = password,
                        onSuccess = {
                            messageBarState.addSuccess("Successfully Authenticated")
                            scope.launch {
                                delay(600)
                                navigateToMain()
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
