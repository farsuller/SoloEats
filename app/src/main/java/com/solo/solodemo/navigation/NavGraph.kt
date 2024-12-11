package com.solo.solodemo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.solo.components.canBackStack
import com.solo.components.routes.ScreensRoutes
import com.solodemo.auth.presenations.forgot.forgotRoute
import com.solodemo.auth.presenations.login.loginRoute
import com.solodemo.auth.presenations.signup.signUpRoute
import com.solodemo.main.presentations.mainRoute
import com.solodemo.main.presentations.placeorder.placeOrderRoute
import com.solodemo.main.presentations.products.productSelectionRoute

@Composable
fun SetupNavGraph(
    startDestination: String,
    navHostController: NavHostController,
    onDataLoaded: () -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
    ) {
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
            onDataLoaded = onDataLoaded,
        )
        mainRoute(
            onDataLoaded = onDataLoaded,
            navigateToAuth = {
                if (navHostController.canBackStack) {
                    navHostController.popBackStack()
                    navHostController.navigate(ScreensRoutes.Auth.route)
                }
            },
            navigateToProductList = { category ->
                if (navHostController.canBackStack) {
                    navHostController.navigate(ScreensRoutes.Product.passCategoryName(categoryName = category))
                }
            },
            navigateToPlaceOrderSuccess = {
                if (navHostController.canBackStack) {
                    navHostController.navigate(ScreensRoutes.PlaceOrder.route)
                }
            },
        )

        productSelectionRoute(onBackPressClicked = {
            if (navHostController.canBackStack) {
                navHostController.popBackStack()
            }
        })

        placeOrderRoute(onNavigateToMain = {
            if (navHostController.canBackStack) {
                navHostController.popBackStack()
                navHostController.navigate(ScreensRoutes.Main.route)
            }
        })

        signUpRoute(onBackPressClicked = {
            if (navHostController.canBackStack) {
                navHostController.popBackStack()
            }
        })

        forgotRoute(onButtonClicked = {
            if (navHostController.canBackStack) {
                navHostController.popBackStack()
            }
        })
    }
}
