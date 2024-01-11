package com.solodemo.main.presentations.screens.menu

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.solodemo.main.components.MainBackground
import com.solodemo.supabase.model.Menu

@Composable
fun MenusScreen(
    paddingValues: PaddingValues,
    menus: List<Menu>,
    navigateToProductList: (String) -> Unit
) {

    val filteredMenus = menus.filter { it.isAvailable }

    MainBackground()
    MenuContent(
        filteredMenus = filteredMenus,
        paddingValues = paddingValues,
        navigateToProductList = navigateToProductList
    )
}

