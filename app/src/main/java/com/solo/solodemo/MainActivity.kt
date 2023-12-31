package com.solo.solodemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.solo.solodemo.navigation.SetupNavGraph
import com.solo.solodemo.theme.SoloDemoTheme


class MainActivity : ComponentActivity() {

    var keepSplashOpened = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition { keepSplashOpened }
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            SoloDemoTheme(dynamicColor = false) {
                val padding by remember { mutableStateOf(PaddingValues()) }
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupNavGraph(
                        startDestination = getStartDestination(),
                        navHostController = navController,
                        onDataLoaded = {
                            keepSplashOpened = false
                        },
                    )
                }
            }
        }
    }
}

private fun getStartDestination(): String {
    return ScreensRoutes.Auth.route
}


