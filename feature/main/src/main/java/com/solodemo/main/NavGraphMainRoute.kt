package com.solodemo.main

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.util.routes.ScreensRoutes
import com.solodemo.supabase.model.RequestState

fun NavGraphBuilder.mainRoute(onDataLoaded: () -> Unit,) {
    composable(route = ScreensRoutes.Main.route) {

        val viewModel: MainViewModel = hiltViewModel()
        val menusList by viewModel.menus

        LaunchedEffect(key1 = menusList) {
            if (menusList !is RequestState.Loading) {
                onDataLoaded()
            }
        }

        MainScreen(menus = menusList)
    }
}