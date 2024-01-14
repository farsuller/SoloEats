package com.solodemo.main.presentations.screens.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.routes.ScreensRoutes
import com.solo.components.state.RequestState
import com.solodemo.main.presentations.MainViewModel
import com.solodemo.supabase.domain.repository.Menus
import kotlinx.coroutines.flow.collectLatest

fun NavGraphBuilder.homeRoute(
    paddingValues: PaddingValues,
    menus: Menus,
    homeLazyListState: LazyListState,
    navigateToProductList: (String) -> Unit,
    viewModel: MainViewModel,
) {
    composable(route = ScreensRoutes.Home.route) {

        val reviewsList by viewModel.reviews
        val foodList = viewModel.getProductList(LocalContext.current)

        HomeScreen(
            paddingValues = paddingValues,
            menus = menus,
            reviews = reviewsList,
            foodList = foodList,
            homeLazyListState = homeLazyListState,
            navigateToProductList = navigateToProductList,
            viewModel = viewModel
        )
    }
}