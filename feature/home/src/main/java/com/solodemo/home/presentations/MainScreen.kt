package com.solodemo.home.presentations

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowInsetsController
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.solodemo.database.domain.model.Cart
import com.solodemo.home.components.MainBottomNavBar
import com.solodemo.home.presentations.dashboard.account.AccountState
import com.solodemo.home.presentations.dashboard.cart.CartState
import com.solodemo.home.presentations.dashboard.home.ReviewsState
import com.solodemo.home.presentations.dashboard.menu.MenusState
import com.solodemo.home.presentations.products.ProductsState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun MainScreen(
    menusState: MenusState,
    reviewsState: ReviewsState,
    cartState: CartState,
    accountState: AccountState,
    productState: ProductsState,
    isLoadingData: Boolean,
    navController: NavHostController = rememberNavController(),
    navigateToAuth: () -> Unit,
    navigateToProductList: (String) -> Unit,
    navigateToPlaceOrderSuccess: () -> Unit,
    insertCart: (Cart) -> Unit,
    onPullRefresh: () -> Unit,
) {
    val window = (LocalView.current.context as Activity).window

    val homeLazyListState by remember { mutableStateOf(LazyListState()) }

    var selectedTab by remember { mutableStateOf("Home") }
    val statusBarColor by animateColorAsState(
        targetValue = when (selectedTab) {
            "Menus" -> MaterialTheme.colorScheme.primary
            "Home" -> MaterialTheme.colorScheme.secondary
            else -> MaterialTheme.colorScheme.surface
        },
        label = "Animate Status Bar",
    )

    DynamicStatusBar(
        window,
        statusBarColor,
        isLightStatusBar = selectedTab == "Home" || selectedTab == "Menus",
    )

    Scaffold(
        bottomBar = {
            MainBottomNavBar(
                navController = navController,
                onTabSelected = { tab -> selectedTab = tab },
                cartCount = cartState.cartList?.size,
            )
        },
    ) {
        if (isLoadingData) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        } else {
            MainContent(
                paddingValues = it,
                navController = navController,
                menusState = menusState,
                reviewsState = reviewsState,
                productState = productState,
                accountState = accountState,
                navigateToAuth = navigateToAuth,
                navigateToProductList = navigateToProductList,
                homeLazyListState = homeLazyListState,
                navigateToPlaceOrderSuccess = navigateToPlaceOrderSuccess,
                insertCart = insertCart,
                onPullRefresh = onPullRefresh,
            )
        }
    }
}

@Composable
private fun DynamicStatusBar(
    window: Window,
    statusBarColor: Color,
    isLightStatusBar: Boolean,
) {
    LaunchedEffect(statusBarColor) {
        window.decorView.rootView.setBackgroundColor(statusBarColor.toArgb())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                if (isLightStatusBar) 0 else WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
            )
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = if (isLightStatusBar) {
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                View.SYSTEM_UI_FLAG_VISIBLE
            }
        }
    }
}
