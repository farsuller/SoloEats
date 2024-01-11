package com.solodemo.main.presentations.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
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
    navigateToProductList: (String) -> Unit
) {

    MainBackground()

    HomeContent(
        paddingValues = paddingValues,
        menus = menus,
        reels = reels,
        homeLazyListState = homeLazyListState,
        navigateToProductList = navigateToProductList
    )

}
