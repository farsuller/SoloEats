package com.solodemo.main.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

enum class MainBottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val hasUpdate: Boolean,
    val badgeCount: Int? = null,
    val route: String,
) {
    Home(
        unSelectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        title = "Home",
        hasUpdate = false,
        route = "home_screen",
    ),
    Menus(
        unSelectedIcon = Icons.Outlined.Description,
        selectedIcon = Icons.Filled.Description,
        title = "Menus",
        hasUpdate = false,
        route = "menu_screen",
    ),
    Wallet(
        unSelectedIcon = Icons.Outlined.AccountBalanceWallet,
        selectedIcon = Icons.Filled.AccountBalanceWallet,
        title = "Payment",
        hasUpdate = true,
        route = "payment_screen",
    ),
    Cart(
        unSelectedIcon = Icons.Outlined.ShoppingCart,
        selectedIcon = Icons.Filled.ShoppingCart,
        title = "Cart",
        hasUpdate = false,
        badgeCount = 1,
        route = "cart_screen",
    ),
    Account(
        unSelectedIcon = Icons.Outlined.AccountCircle,
        selectedIcon = Icons.Filled.AccountCircle,
        title = "Account",
        hasUpdate = false,
        route = "account_screen",
    ),
}
