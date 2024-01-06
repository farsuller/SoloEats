package com.solodemo.auth.presenations.login

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.dialogs.DisplayAlertDialog
import com.solo.components.state.RequestState
import com.solo.util.routes.ScreensRoutes
import com.solodemo.auth.presenations.AuthViewModel
import kotlinx.coroutines.flow.collectLatest


fun NavGraphBuilder.loginRoute(
    navigateToMain: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToForgot: () -> Unit,
    onDataLoaded: () -> Unit
) {
    composable(route = ScreensRoutes.Auth.route) {
        val context = LocalContext.current
        val authViewModel = hiltViewModel<AuthViewModel>()

        var openErrorDialog by remember {
            mutableStateOf(false)
        }

        LaunchedEffect(key1 = authViewModel.uiState) {
            onDataLoaded()

            authViewModel.uiState.collectLatest { data->
                when(data){
                    RequestState.Loading -> {}
                    is RequestState.Success -> {
                        navigateToMain()
                        authViewModel.setLoading(false)
                    }
                    is RequestState.Error -> {
                        authViewModel.setLoading(false)
                        openErrorDialog = true
                    }
                    else ->{

                    }
                }
            }
        }
        LoginScreen(
            onGoogleButtonClicked = { navigateToMain() },
            onForgotButtonClicked = { navigateToForgot() },
            onSignUpButtonClicked = { navigateToSignUp() },
            authViewModel = authViewModel
        )


        DisplayAlertDialog(
            title = "Invalid Login",
            message = "Oops! Invalid login credentials. Make sure your email and password are correct.",
            dialogOpened = openErrorDialog,
            onCloseDialog = { openErrorDialog = false },
            onYesClicked = {},
            positiveText = "Okay",
            showNegativeButton = false
        )
    }
}




