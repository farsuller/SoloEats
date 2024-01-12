package com.solodemo.main.presentations.screens.menu

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes
import com.solodemo.supabase.domain.repository.Menus

fun NavGraphBuilder.menuRoute(
    paddingValues: PaddingValues,
    menus: Menus,
    navigateToProductList: (String) -> Unit
) {
    composable(route = ScreensRoutes.Menu.route) {
        MenusScreen(
            paddingValues = paddingValues,
            menus = menus,
            navigateToProductList = navigateToProductList
        )
    }
}