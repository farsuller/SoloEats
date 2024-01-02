package com.solodemo.main.screens.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.CircularLoadingIndicator
import com.solo.components.EmptyPage
import com.solo.util.routes.ScreensRoutes
import com.solodemo.supabase.model.RequestState
import com.solodemo.supabase.repository.Menus

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
                EmptyPage()
            }

            else -> {}
        }

    }
}