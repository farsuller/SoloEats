package com.solodemo.auth.presenations.signup

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.dialogs.DisplayAlertDialog
import com.solo.components.routes.ScreensRoutes
import com.solo.components.state.RequestState
import com.solodemo.auth.presenations.AuthViewModel
import kotlinx.coroutines.flow.collectLatest

fun NavGraphBuilder.signUpRoute(onBackPressClicked: () -> Unit) {
    composable(route = ScreensRoutes.SignUp.route) {
        val authViewModel = hiltViewModel<AuthViewModel>()

        var openDialog by remember {
            mutableStateOf(false)
        }

        var openErrorDialog by remember {
            mutableStateOf(false)
        }

        LaunchedEffect(key1 = authViewModel.uiState) {
            authViewModel.uiState.collectLatest { data ->
                when (data) {
                    RequestState.Loading -> {}
                    is RequestState.Success -> {
                        openDialog = true
                        authViewModel.setLoading(false)
                    }

                    is RequestState.Error -> {
                        openErrorDialog = true
                        authViewModel.setLoading(false)
                    }

                    else -> {}
                }
            }
        }

        SignUpScreen(
            onBackPressClicked = onBackPressClicked,
            authViewModel = authViewModel,
        )

        DisplayAlertDialog(
            title = "Sign Up Success",
            message = "Success! Check your email for a verification link to complete your sign-up. Welcome to SoloEats!",
            dialogOpened = openDialog,
            onCloseDialog = { openDialog = false },
            onYesClicked = onBackPressClicked,
            positiveText = "Okay",
            showNegativeButton = false,
        )

        DisplayAlertDialog(
            title = "Sign Up Failed",
            message = "Oops! It seems there's an issue with your signup. Please review your information and try again.",
            dialogOpened = openErrorDialog,
            onCloseDialog = { openErrorDialog = false },
            onYesClicked = {},
            positiveText = "Okay",
            showNegativeButton = false,
        )
    }
}
