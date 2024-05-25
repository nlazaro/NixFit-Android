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
import androidx.lifecycle.lifecycleScope
import com.example.nixfit.data.local.FoodDao
import com.example.nixfit.domain.model.Nutriments
import com.example.nixfit.domain.model.Product
import com.example.nixfit.presentation.navigation.NavGraph
import com.example.nixfit.ui.theme.NixFitTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var dao: FoodDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        lifecycleScope.launch{
            dao.upsert(
                Product(
                    productName = "Redbull",
                    servingSize = "100",
                    servingQuantity = "ml",
                    nutriments = Nutriments(
                        carbohydrates = "100",
                        energy = "100",
                        fat = "100",
                        protein = "100",
                        sodium = "100",
                        sugar = "100"
                    )
                )
            )
        }


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

