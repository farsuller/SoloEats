package com.solodemo.main.presentations.screens.menu

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.contents.EmptyContent
import com.solo.components.loading.CircularLoadingIndicator
import com.solo.components.routes.ScreensRoutes
import com.solo.components.state.RequestState
import com.solodemo.supabase.domain.repository.Menus

fun NavGraphBuilder.menuRoute(
    paddingValues: PaddingValues,
    menus: Menus,
    navigateToProductList: (String) -> Unit
) {
    composable(route = ScreensRoutes.Menu.route) {


        when (menus) {
            is RequestState.Loading -> {
                CircularLoadingIndicator()
            }

            is RequestState.Success -> {
                MenusScreen(
                    paddingValues = paddingValues,
                    menus = menus.data,
                    navigateToProductList = navigateToProductList
                )
            }

            is RequestState.Error -> {
                EmptyContent()
            }

            else -> {}
        }

    }
}