package com.solodemo.main.screens.menu

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.loading.CircularLoadingIndicator
import com.solo.components.contents.EmptyContent
import com.solo.util.routes.ScreensRoutes
import com.solo.components.state.RequestState
import com.solodemo.supabase.domain.repository.Menus

fun NavGraphBuilder.menuRoute(paddingValues: PaddingValues, menus: Menus) {
    composable(route = ScreensRoutes.Menu.route) {


        when (menus) {
            is RequestState.Loading -> {
                CircularLoadingIndicator()
            }

            is RequestState.Success -> {
                MenusScreen(
                    modifier = Modifier
                        .padding(start = 40.dp)
                        .fillMaxSize()
                        .padding(top = paddingValues.calculateTopPadding())
                        .padding(bottom = paddingValues.calculateBottomPadding()),
                    menus = menus.data
                )
            }

            is RequestState.Error -> {
                EmptyContent()
            }

            else -> {}
        }

    }
}