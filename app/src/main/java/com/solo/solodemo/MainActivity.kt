package com.solo.solodemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.solo.components.routes.ScreensRoutes
import com.solo.components.theme.SoloDemoTheme
import com.solo.solodemo.navigation.SetupNavGraph
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.request.get

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var keepSplashOpened = true

    // private val authViewModel by viewModels<AuthViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition { keepSplashOpened }
        setContent {
            SoloDemoTheme(
                darkTheme = false,
                dynamicColor = false,
            ) {
                val navController = rememberNavController()
                var getStartDestination by remember { mutableStateOf<String?>(null) }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    SetupNavGraph(
                        startDestination = ScreensRoutes.Main.route,
                        navHostController = navController,
                        onDataLoaded = {
                            keepSplashOpened = false
                        },
                    )

//                    LaunchedEffect(key1 = authViewModel) {
//                        authViewModel.isUserLoggedIn()
//
//                        authViewModel.uiState.collectLatest { data ->
//                            when (data) {
//                                RequestState.Loading -> {}
//                                is RequestState.Success -> getStartDestination = ScreensRoutes.Main.route
//                                is RequestState.Error -> getStartDestination = ScreensRoutes.Auth.route
//                                else -> {}
//                            }
//                        }
//                    }
                    getStartDestination?.let { startDestination ->
                    }
                }
            }
        }
    }
}
