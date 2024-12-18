package com.solo.solodemo

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.solo.components.routes.ScreensRoutes
import com.solo.components.state.AuthState
import com.solo.components.theme.SoloDemoTheme
import com.solo.solodemo.navigation.SetupNavGraph
import com.solodemo.auth.presenations.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var keepSplashOpened = true

    private val authViewModel by viewModels<AuthViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT))
        installSplashScreen().setKeepOnScreenCondition { keepSplashOpened }
        setContent {
            SoloDemoTheme(
                darkTheme = false,
                dynamicColor = false,
            ) {
                val authState = authViewModel.authState.collectAsStateWithLifecycle()
                val context = LocalContext.current
                val navController = rememberNavController()
                var getStartDestination by remember { mutableStateOf<String?>(null) }

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    LaunchedEffect(authState.value) {
                        keepSplashOpened = true
                        when (authState.value) {
                            is AuthState.Unauthenticated -> getStartDestination = ScreensRoutes.Auth.route
                            is AuthState.Authenticated -> getStartDestination = ScreensRoutes.Main.route
                            is AuthState.Error -> Toast.makeText(context, (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
                            else -> Unit
                        }
                    }

                    getStartDestination?.let { startDestination ->
                        SetupNavGraph(
                            startDestination = startDestination,
                            navHostController = navController,
                            onDataLoaded = {
                                keepSplashOpened = it
                            },
                        )
                    }
                }
            }
        }
    }
}
