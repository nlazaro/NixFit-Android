package com.example.nixfit.presentation.nvgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.nixfit.presentation.onboarding.OnBoardingScreen
import com.example.nixfit.presentation.onboarding.OnBoardingViewModel


@Composable
fun NavGraph (
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ){
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                val viewModel : OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = viewModel::onEvent
                )
            }
        }
        navigation(
            route =Route.TempNavigation.route,
            startDestination = Route.TempNavigatorScreen.route
        ){
            composable(
                route = Route.TempNavigatorScreen.route
            ) {
                Text(text = "temp screen")
            }
        }
    }
}