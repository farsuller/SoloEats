package com.solo.solodemo.presentations.main.screens.menu

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.solodemo.ScreensRoutes
import com.solo.solodemo.presentations.main.screens.payment.menu.MenuSelectionScreen

fun NavGraphBuilder.menuRoute(paddingValues: PaddingValues) {
    composable(route = ScreensRoutes.Menu.route) {

        MenuSelectionScreen(modifier = Modifier
            .padding(start = 40.dp)
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .padding(bottom = paddingValues.calculateBottomPadding()))
    }
}