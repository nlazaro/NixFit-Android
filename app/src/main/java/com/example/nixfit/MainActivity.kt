package com.example.nixfit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.example.nixfit.domain.manager.AppEntryUseCases
import com.example.nixfit.presentation.onboarding.OnBoardingScreen
import com.example.nixfit.presentation.onboarding.OnBoardingViewModel
import com.example.nixfit.ui.theme.NixFitTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nixfit.presentation.nvgraph.NavGraph
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().apply{
            setKeepOnScreenCondition{
                viewModel.splashCondition
            }
        }
        setContent {
            NixFitTheme{
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background))
                {
                    val startDestination = viewModel.startDestination
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}

