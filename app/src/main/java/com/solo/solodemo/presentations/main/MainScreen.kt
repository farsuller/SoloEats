package com.solo.solodemo.presentations.main

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.solo.solodemo.presentations.main.components.MainBottomNavBar
import com.solo.solodemo.presentations.main.components.MainTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun MainScreen(navController: NavHostController = rememberNavController()) {

    Scaffold(
        topBar = {
            MainTopBar()
        },
        bottomBar = {
            MainBottomNavBar(navController)
        }
    ){
        MainContent(paddingValues = it, navController = navController)
    }
}









