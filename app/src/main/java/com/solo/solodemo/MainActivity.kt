package com.solo.solodemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.solo.solodemo.navigation.SetupNavGraph
import com.solo.ui.theme.SoloDemoTheme
import com.solo.util.routes.ScreensRoutes
import com.solodemo.auth.presenations.AuthViewModel
import com.solodemo.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var keepSplashOpened = true
    private val authViewModel by viewModels<AuthViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition { keepSplashOpened }
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            SoloDemoTheme(
                darkTheme = false,
                dynamicColor = false) {
                val navController = rememberNavController()
                var getStartDestination by remember { mutableStateOf<String?>(null) }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LaunchedEffect(key1 = authViewModel){
                        authViewModel.isUserLoggedIn { isAuthenticated ->
                            getStartDestination = if (isAuthenticated) ScreensRoutes.Main.route
                            else ScreensRoutes.Auth.route
                        }
                    }
                    getStartDestination?.let { startDestination ->
                        SetupNavGraph(
                            startDestination = startDestination,
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
}



