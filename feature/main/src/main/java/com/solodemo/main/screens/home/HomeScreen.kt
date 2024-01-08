package com.solodemo.main.screens.home

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import com.solodemo.main.components.MainBackground
import com.solodemo.supabase.domain.repository.Menus
import com.solodemo.supabase.domain.repository.Reels

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun HomeScreen(
    paddingValues: PaddingValues,
    menus: Menus,
    reels: Reels,
    homeLazyListState: LazyListState,
) {

    MainBackground()

    HomeContent(
        paddingValues = paddingValues,
        menus = menus,
        reels = reels,
        homeLazyListState = homeLazyListState
    )

}
