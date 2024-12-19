package com.solodemo.main.presentations.dashboard.menu

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes
import com.solodemo.main.components.MainBackground

fun NavGraphBuilder.menuRoute(
    paddingValues: PaddingValues,
    menusState: MenusState,
    navigateToProductList: (String) -> Unit,
) {
    composable(route = ScreensRoutes.Menu.route) {
        MainBackground()
        MenuContent(
            menusState = menusState,
            paddingValues = paddingValues,
            navigateToProductList = navigateToProductList,
        )
    }
}
