package com.solo.solodemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.solo.solodemo.navigation.SetupNavGraph
import com.solo.ui.theme.SoloDemoTheme
import com.solo.util.routes.ScreensRoutes
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    var keepSplashOpened = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition { keepSplashOpened }
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            SoloDemoTheme(dynamicColor = false) {
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


