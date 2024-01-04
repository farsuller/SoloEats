package com.solodemo.auth.presenations.login

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
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

        val authViewModel = hiltViewModel<AuthViewModel>()

        LaunchedEffect(key1 = Unit) {
            onDataLoaded()

            authViewModel.uiState.collectLatest { data->
                when(data){
                    RequestState.Loading -> {}
                    is RequestState.Success -> {
                        navigateToMain()
                    }
                    is RequestState.Error -> {}
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




