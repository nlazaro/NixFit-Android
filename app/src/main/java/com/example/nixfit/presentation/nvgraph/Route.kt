package com.example.nixfit.presentation.nvgraph

sealed class Route (
    val route: String
){
    object OnBoardingScreen : Route(route = "onBoardingScreen")
    object HomeScreen : Route(route = "homeScreen")
    object ProfileScreen : Route(route = "profileScreen")
    object DashboardScreen : Route(route = "dashboardScreen")
    object AppStartNavigation : Route(route = "appStartNavigation")
    object TempNavigation : Route(route = "tempNavigation")

    object TempNavigatorScreen : Route(route = "tempNavigatorScreen")

}