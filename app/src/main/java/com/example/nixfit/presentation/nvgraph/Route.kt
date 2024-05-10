package com.example.nixfit.presentation.nvgraph

sealed class Route (
    val route: String
){
    object HomeScreen : Route(route = "homeScreen")
    object ProfileScreen : Route(route = "profileScreen")
    object DashboardScreen : Route(route = "dashboardScreen")
    object AppStartNavigation : Route(route = "appStartNavigation")

}