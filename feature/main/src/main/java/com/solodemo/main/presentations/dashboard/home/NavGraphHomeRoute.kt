package com.solodemo.main.presentations.dashboard.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes
import com.solodemo.database.domain.model.Cart
import com.solodemo.main.presentations.dashboard.menu.MenusState
import com.solodemo.main.presentations.products.ProductsState
import com.stevdzasan.messagebar.ContentWithMessageBar
import com.stevdzasan.messagebar.MessageBarPosition
import com.stevdzasan.messagebar.rememberMessageBarState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun NavGraphBuilder.homeRoute(
    paddingValues: PaddingValues,
    menusState: MenusState,
    reviewsState: ReviewsState,
    productState: ProductsState,
    homeLazyListState: LazyListState,
    navigateToProductList: (String) -> Unit,
    insertCart: (Cart) -> Unit,
    onPullRefresh: () -> Unit,
) {
    composable(route = ScreensRoutes.Home.route) {
        var isRefreshing by remember { mutableStateOf(false) }
        val scope = rememberCoroutineScope()
        val messageBarState = rememberMessageBarState()

        val pullRefreshState = rememberPullRefreshState(
            refreshing = isRefreshing,
            onRefresh = {
                scope.launch {
                    isRefreshing = true
                    delay(500)
                    onPullRefresh()
                    isRefreshing = false
                }
            },
        )

        ContentWithMessageBar(
            messageBarState = messageBarState,
            showCopyButton = false,
            position = MessageBarPosition.BOTTOM,
        ) {
            Box(
                modifier = Modifier
                    .pullRefresh(pullRefreshState),
            ) {
                HomeScreen(
                    paddingValues = paddingValues,
                    menusState = menusState,
                    reviewsState = reviewsState,
                    productState = productState,
                    homeLazyListState = homeLazyListState,
                    navigateToProductList = navigateToProductList,
                    popularAddToCartClicked = { cart ->
                        insertCart(cart)
                    },
                    messageBarState = messageBarState,
                )

                PullRefreshIndicator(
                    refreshing = isRefreshing,
                    state = pullRefreshState,
                    modifier = Modifier
                        .zIndex(1f)
                        .align(Alignment.TopCenter),
                )
            }
        }
    }
}
