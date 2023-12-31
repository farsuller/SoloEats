package com.solo.solodemo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.solo.solodemo.ScreensRoutes
import com.solo.solodemo.presentations.auth.forgot.forgotRoute
import com.solo.solodemo.presentations.auth.login.loginRoute
import com.solo.solodemo.presentations.auth.signup.signUpRoute
import com.solo.solodemo.presentations.main.mainRoute


@Composable
fun SetupNavGraph(
    startDestination: String,
    navHostController: NavHostController,
    onDataLoaded: () -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    )
    {
        loginRoute(
            navigateToMain = {
                navHostController.popBackStack()
                navHostController.navigate(ScreensRoutes.Main.route)
            },
            navigateToSignUp = {

                navHostController.navigate(ScreensRoutes.SignUp.route)
            },
            navigateToForgot = {

                navHostController.navigate(ScreensRoutes.Forgot.route)
            },
            onDataLoaded = onDataLoaded
        )
        mainRoute()
        signUpRoute(onButtonClicked = { navHostController.popBackStack() })
        forgotRoute(onButtonClicked = { navHostController.popBackStack() })

    }
}






