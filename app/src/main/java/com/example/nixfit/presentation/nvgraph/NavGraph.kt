package com.example.nixfit.presentation.nvgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation


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
            startDestination = Route.HomeScreen.route
        ){
            composable(
                route = Route.HomeScreen.route
            ) {
               Text(text = "Home")
            }
        }
    }
}