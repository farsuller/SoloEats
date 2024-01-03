package com.solodemo.main

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.solodemo.main.components.MainBottomNavBar
import com.solodemo.main.components.MainTopBar
import com.solodemo.supabase.domain.repository.Menus


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun MainScreen(
    menus: Menus,
    navController: NavHostController = rememberNavController()) {

    var selectedTab by remember { mutableStateOf("Home") }
    Scaffold(
        topBar = {
            MainTopBar(selectedTab = selectedTab)
        },
        bottomBar = {
            MainBottomNavBar(navController = navController, onTabSelected = {tab -> selectedTab = tab })
        }
    ){
        MainContent(paddingValues = it, navController = navController, menus = menus)
    }
}









