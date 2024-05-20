package com.example.nixfit.presentation.navigation

sealed class Screen(
    val route: String,
){
    data object AppStart : Screen("appStart")
    data object AppMain : Screen("appMain")
    data object Onboarding : Screen("onboarding")
    data object Dashboard : Screen("dashboard")
    data object FoodDiary : Screen("foodDiary")
    data object Recipe : Screen("recipe")
}