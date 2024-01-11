package com.solodemo.main.presentations.products

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.Constants
import com.solo.components.Constants.DEFAULT_CATEGORY_NAME
import com.solo.components.routes.ScreensRoutes
import com.solodemo.main.presentations.MainViewModel

fun NavGraphBuilder.productSelectionRoute(onBackPressClicked: () -> Unit) {
    composable(route = ScreensRoutes.Product.route,
        enterTransition = {
            // Custom enter transition (push up)
            slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(400)
            ) + fadeIn(animationSpec = tween(400))
        },
        exitTransition = {
            // Custom exit transition (slide down)
            slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(400)
            ) + fadeOut(animationSpec = tween(400))
        }) { backstackEntry ->

        val viewModel = hiltViewModel<MainViewModel>()
        val selectedCategory = backstackEntry.arguments?.getString(Constants.CATEGORY_NAME_ARG_KEY)
            ?: DEFAULT_CATEGORY_NAME

        val foodList = viewModel.getProductList(LocalContext.current)

        ProductListScreen(
            onBackPressClicked = onBackPressClicked,
            foodList = foodList,
            categoryNameSelected = selectedCategory
        )

    }
}




