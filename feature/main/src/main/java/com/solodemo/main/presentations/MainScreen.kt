package com.solodemo.main.presentations

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.solodemo.main.components.MainBottomNavBar
import com.solodemo.main.components.MainTopBar
import com.solodemo.supabase.domain.repository.Menus
import com.solodemo.supabase.domain.repository.Users


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun MainScreen(
    menus: Menus,
    users: Users,
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel,
    navigateToAuth: () -> Unit,
    navigateToProductList: (String) -> Unit
) {
    val view = LocalView.current
    val window = (view.context as Activity).window

    val homeLazyListState by remember { mutableStateOf(LazyListState()) }

    val changeStatusBar by remember {
        derivedStateOf {
            homeLazyListState.firstVisibleItemScrollOffset > 0
        }
    }
    var selectedTab by remember { mutableStateOf("Home") }
    val statusBarColor by animateColorAsState(
        if (changeStatusBar) MaterialTheme.colorScheme.surface
        else MaterialTheme.colorScheme.secondary, label = "Animate Status Bar"
    )


    when (selectedTab) {
        "Payment", "Cart" -> window.statusBarColor = MaterialTheme.colorScheme.surface.toArgb()
        "Menus" -> window.statusBarColor = MaterialTheme.colorScheme.primary.toArgb()
        "Account" -> window.statusBarColor = MaterialTheme.colorScheme.secondary.toArgb()
        else -> window.statusBarColor = statusBarColor.toArgb()
    }

    Scaffold(
        topBar = {
            MainTopBar(selectedTab = selectedTab)
        },
        bottomBar = {
            MainBottomNavBar(
                navController = navController,
                onTabSelected = { tab -> selectedTab = tab })
        }
    ) {
        MainContent(
            paddingValues = it,
            navController = navController,
            menus = menus,
            users = users,
            viewModel = viewModel,
            navigateToAuth = navigateToAuth,
            navigateToProductList = navigateToProductList,
            homeLazyListState = homeLazyListState,
        )


    }
}









