package com.solodemo.main.screens.menu

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.solodemo.supabase.model.Menu

@Composable
fun MenusScreen(paddingValues: PaddingValues, menus: List<Menu>) {

    val filteredMenus = menus.filter { it.isAvailable }

    MenuContent(filteredMenus = filteredMenus, paddingValues = paddingValues)
}

