package com.solodemo.home.presentations.products

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.Constants
import com.solo.components.Constants.DEFAULT_CATEGORY_NAME
import com.solo.components.routes.ScreensRoutes
import com.solodemo.home.presentations.MainViewModel
import com.solodemo.home.presentations.dashboard.home.HomeEvent
import com.stevdzasan.messagebar.ContentWithMessageBar
import com.stevdzasan.messagebar.MessageBarPosition
import com.stevdzasan.messagebar.rememberMessageBarState

fun NavGraphBuilder.productSelectionRoute(onBackPressClicked: () -> Unit) {
    composable(
        route = ScreensRoutes.Product.route,
        enterTransition = {
            slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(400),
            ) + fadeIn(animationSpec = tween(400))
        },
        exitTransition = {
            slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(400),
            ) + fadeOut(animationSpec = tween(400))
        },
    ) { backstackEntry ->

        val selectedCategory = backstackEntry.arguments?.getString(Constants.CATEGORY_NAME_ARG_KEY)
            ?: DEFAULT_CATEGORY_NAME

        val viewModel = hiltViewModel<MainViewModel>()

        val productState by viewModel.productsState.collectAsStateWithLifecycle()
        val loadData by viewModel.isLoadingData.collectAsStateWithLifecycle()
        val messageBarState = rememberMessageBarState()

        if (loadData) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        } else {
            ContentWithMessageBar(
                messageBarState = messageBarState,
                showCopyButton = false,
                position = MessageBarPosition.BOTTOM,
            ) {
                ProductListScreen(
                    onBackPressClicked = onBackPressClicked,
                    productsState = productState,
                    categoryNameSelected = selectedCategory,
                    addToCartItem = {
                        viewModel.onEvent(
                            event = HomeEvent.UpsertCartItem(it),
                            onSuccess = {
                                messageBarState.addSuccess("${it.productDetails?.name} Added to Cart")
                            },
                        )
                    },
                )
            }
        }
    }
}
