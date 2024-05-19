package com.example.nixfit.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.nixfit.presentation.common.AppBars
import com.example.nixfit.presentation.onboarding.OnBoardingScreen
import com.example.nixfit.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // onboarding graph
        navigation(
            route = Screen.AppStart.route,
            startDestination = Screen.Onboarding.route
        ) {
            composable(route = Screen.Onboarding.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(viewModel::onEvent)
            }
        }
        // rest of the app
        navigation(
            route = Screen.AppMain.route,
            startDestination = Screen.Dashboard.route
        ) {
            composable(route = Screen.Dashboard.route) {
                AppBars()
            }
        }
    }
}