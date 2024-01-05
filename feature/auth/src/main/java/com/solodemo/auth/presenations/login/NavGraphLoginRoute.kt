package com.solodemo.auth.presenations.login

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
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

        LaunchedEffect(key1 = Unit) {
            onDataLoaded()

            authViewModel.uiState.collectLatest { data->
                when(data){
                    RequestState.Loading -> {}
                    is RequestState.Success -> {
                        navigateToMain()
                    }
                    is RequestState.Error -> {
                        Toast.makeText(context, "Invalid Login", Toast.LENGTH_SHORT).show()
                    }
                    else ->{}
                }
            }
        }
        LoginScreen(
            onGoogleButtonClicked = { navigateToMain() },
            onForgotButtonClicked = { navigateToForgot() },
            onSignUpButtonClicked = { navigateToSignUp() },
            authViewModel = authViewModel
        )
    }
}




