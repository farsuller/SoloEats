package com.solodemo.main.presentations.dashboard.menu

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.solodemo.main.components.MainBackground
import com.solodemo.supabase.domain.repository.Menus

@Composable
fun MenusScreen(
    paddingValues: PaddingValues,
    menus: Menus,
    navigateToProductList: (String) -> Unit
) {
    MainBackground()
    MenuContent(
        menus = menus,
        paddingValues = paddingValues,
        navigateToProductList = navigateToProductList
    )
}

