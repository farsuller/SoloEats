package com.solo.solodemo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.solo.util.routes.ScreensRoutes
import com.solodemo.auth.presenations.forgot.forgotRoute
import com.solodemo.auth.presenations.login.loginRoute
import com.solodemo.auth.presenations.signup.signUpRoute
import com.solodemo.main.mainRoute


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
        mainRoute(onDataLoaded = onDataLoaded)
        signUpRoute(onButtonClicked = { navHostController.popBackStack() })
        forgotRoute(onButtonClicked = { navHostController.popBackStack() })

    }
}






