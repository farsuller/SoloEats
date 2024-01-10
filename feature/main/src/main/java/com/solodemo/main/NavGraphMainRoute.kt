package com.solodemo.main

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.util.routes.ScreensRoutes
import com.solo.components.state.RequestState

fun NavGraphBuilder.mainRoute(onDataLoaded: () -> Unit, navigateToAuth: () -> Unit) {
    composable(route = ScreensRoutes.Main.route) {

        val viewModel = hiltViewModel<MainViewModel>()
        val menusList by viewModel.menus
        val reelsList by viewModel.reels

        val foodList = viewModel.getProductList(LocalContext.current)




        LaunchedEffect(key1 = menusList) {
            if (menusList !is RequestState.Loading) {
                onDataLoaded()
            }
        }

        MainScreen(
            reels = reelsList,
            menus = menusList,
            viewModel = viewModel,
            navigateToAuth = navigateToAuth,
            foodList = foodList)
    }
}