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
import com.solodemo.database.domain.model.Cart
import com.solodemo.main.components.MainBottomNavBar
import com.solodemo.main.components.MainTopBar
import com.solodemo.main.model.FoodCategory
import com.solodemo.main.presentations.dashboard.account.AccountState
import com.solodemo.main.presentations.dashboard.cart.CartState
import com.solodemo.supabase.domain.repository.Menus
import com.solodemo.supabase.domain.repository.Reviews

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun MainScreen(
    menus: Menus,
    reviews: Reviews,
    cartState: CartState,
    accountState: AccountState,
    foodList: List<FoodCategory>,
    navController: NavHostController = rememberNavController(),
    navigateToAuth: () -> Unit,
    navigateToProductList: (String) -> Unit,
    navigateToPlaceOrderSuccess: () -> Unit,
    insertCart: (Cart) -> Unit,
) {
    val window = (LocalView.current.context as Activity).window

    val homeLazyListState by remember { mutableStateOf(LazyListState()) }

    val changeStatusBar by remember {
        derivedStateOf {
            homeLazyListState.firstVisibleItemScrollOffset > 0
        }
    }
    var selectedTab by remember { mutableStateOf("Home") }
    val statusBarColor by animateColorAsState(
        if (changeStatusBar) {
            MaterialTheme.colorScheme.surface
        } else MaterialTheme.colorScheme.secondary,
        label = "Animate Status Bar",
    )

    val color = when (selectedTab) {
        "Payment", "Cart" -> MaterialTheme.colorScheme.surface.toArgb()
        "Menus" -> MaterialTheme.colorScheme.primary.toArgb()
        "Account" -> MaterialTheme.colorScheme.secondary.toArgb()
        else -> statusBarColor.toArgb()
    }
    window.decorView.rootView.setBackgroundColor(color)

    Scaffold(
        topBar = {
            MainTopBar(selectedTab = selectedTab)
        },
        bottomBar = {
            MainBottomNavBar(
                navController = navController,
                onTabSelected = { tab -> selectedTab = tab },
                cartCount = cartState.cartList?.size,
            )
        },
    ) {
        MainContent(
            paddingValues = it,
            navController = navController,
            menus = menus,
            reviews = reviews,
            foodList = foodList,
            cartState = cartState,
            accountState = accountState,
            navigateToAuth = navigateToAuth,
            navigateToProductList = navigateToProductList,
            homeLazyListState = homeLazyListState,
            navigateToPlaceOrderSuccess = navigateToPlaceOrderSuccess,
            insertCart = insertCart,
        )
    }
}
