package com.solo.components.routes

import com.solo.components.Constants.CATEGORY_NAME_ARG_KEY


sealed class ScreensRoutes(val route: String) {
    data object Root : ScreensRoutes(route = "root_screen")

    data object Auth : ScreensRoutes(route = "auth_screen")
    data object SignUp : ScreensRoutes(route = "signup_screen")
    data object Forgot : ScreensRoutes(route = "forgot_screen")

    data object Main : ScreensRoutes(route = "main_screen")
    data object Home : ScreensRoutes(route = "home_screen")
    data object Menu : ScreensRoutes(route = "menu_screen")
    data object Payment : ScreensRoutes(route = "payment_screen")
    data object Cart : ScreensRoutes(route = "cart_screen")
    data object Account : ScreensRoutes(route = "account_screen")

    data object Product : ScreensRoutes(route = "product_screen?$CATEGORY_NAME_ARG_KEY={$CATEGORY_NAME_ARG_KEY}"){
        fun passCategoryName(categoryName: String) = "product_screen?$CATEGORY_NAME_ARG_KEY=$categoryName"
    }

}
