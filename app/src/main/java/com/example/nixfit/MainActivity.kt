package com.example.nixfit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.example.nixfit.presentation.onboarding.OnBoardingScreen
import com.example.nixfit.ui.theme.NixFitTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        //enableEdgeToEdge()
        installSplashScreen()
        setContent {
            NixFitTheme{
                Box(
                    modifier = Modifier.background(
                        color = MaterialTheme.colorScheme.background
                    ))
                {
                    OnBoardingScreen()
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    NixFitTheme {
        OnBoardingScreen()
    }
}
