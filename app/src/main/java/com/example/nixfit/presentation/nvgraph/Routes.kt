package com.example.nixfit.presentation.nvgraph

sealed class Route(
    val route: String,
    ) {
    object AppStartNavigation : Route(route = "appStartNavigation")
    object AppMainNavigation : Route(route = "appMainNavigation")
    object OnBoardingScreen : Route(route = "onBoardingScreen")
    object DashboardScreen : Route(route = "dashboardScreen")
    object FoodDiaryScreen : Route(route = "foodDiaryScreen")
    object RecipeScreen : Route(route = "recipeScreen")
}