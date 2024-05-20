package com.example.nixfit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.example.nixfit.presentation.navigation.NavGraph
import com.example.nixfit.ui.theme.NixFitTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().apply{
            setKeepOnScreenCondition{
                viewModel.splashCondition.value
            }
        }
        setContent {
            NixFitTheme{
                val isSystemInDarkMode = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()
                SideEffect{
                    systemController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemInDarkMode
                    )
                }
                Box(modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.background).fillMaxSize()
                    .systemBarsPadding()
                ) {
                    NavGraph(startDestination = viewModel.startDestination.value)
                }
            }
        }
    }
}

