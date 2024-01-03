package com.solodemo.main.screens.home

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.PaddingValues
import com.solodemo.supabase.domain.repository.Menus

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun HomeScreen(paddingValues: PaddingValues, menus: Menus) {
    HomeContent(paddingValues = paddingValues , menus = menus )

}
