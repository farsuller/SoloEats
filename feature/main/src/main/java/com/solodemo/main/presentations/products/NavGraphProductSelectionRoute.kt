package com.solodemo.main.presentations.products

import android.widget.Toast
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.Constants
import com.solo.components.Constants.DEFAULT_CATEGORY_NAME
import com.solo.components.routes.ScreensRoutes
import com.solo.components.state.RequestState
import com.solodemo.main.presentations.MainViewModel
import kotlinx.coroutines.flow.collectLatest

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

        val selectedCategory = backstackEntry.arguments?.getString(Constants.CATEGORY_NAME_ARG_KEY) ?: DEFAULT_CATEGORY_NAME
        val viewModel = hiltViewModel<MainViewModel>()
        val foodList = viewModel.getProductList(LocalContext.current)

        val context = LocalContext.current

        ProductListScreen(
            onBackPressClicked = onBackPressClicked,
            foodList = foodList,
            categoryNameSelected = selectedCategory,
            mainViewModel = viewModel,
            onSuccess = {
                Toast.makeText(context,"Success! Your item has been added to the cart",
                    Toast.LENGTH_SHORT).show()
            },
            onError = {
                Toast.makeText(context,"Failed! Your item has been existing to the cart",
                    Toast.LENGTH_SHORT).show()
            })

    }
}




