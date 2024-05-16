package com.example.nixfit.presentation.common

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nixfit.R
import com.example.nixfit.presentation.dashboard.DashboardScreen
import com.example.nixfit.presentation.dashboard.DashboardViewModel
import com.example.nixfit.presentation.fooddiary.FoodDiaryScreen
import com.example.nixfit.presentation.nvgraph.Route
import com.example.nixfit.presentation.recipe.RecipeScreen


@Composable
fun MainNavigator() {
    val bottomNavigationItems = remember{
        listOf(
            BottomNavigationItem(icon = R.drawable.dashboard, text = "Dashboard"),
            BottomNavigationItem(icon = R.drawable.food_diary, text = "Food Log"),
            BottomNavigationItem(icon = R.drawable.food_recipe, text = "Recipes")
        )
    }
    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Route.DashboardScreen.route -> 0
        Route.FoodDiaryScreen.route -> 1
        Route.RecipeScreen.route -> 2
        else -> 0
    }

    // Hide the bottom navigation when the user is in the details screen
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.DashboardScreen.route ||
                backStackState?.destination?.route == Route.FoodDiaryScreen.route ||
                backStackState?.destination?.route == Route.RecipeScreen.route
    }


    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomBarVisible) {
            BottomNavigation(
                items = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController,
                            route = Route.DashboardScreen.route
                        )

                        1 -> navigateToTab(
                            navController = navController,
                            route = Route.FoodDiaryScreen.route
                        )

                        2 -> navigateToTab(
                            navController = navController,
                            route = Route.RecipeScreen.route
                        )
                    }
                }
            )
        }
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.DashboardScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.DashboardScreen.route) {
                val viewModel: DashboardViewModel = hiltViewModel()
                DashboardScreen()
            }
            composable(route = Route.FoodDiaryScreen.route){
                FoodDiaryScreen()
            }
            composable(route = Route.RecipeScreen.route){
                RecipeScreen()
            }
        }
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Route.DashboardScreen.route
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let {
            popUpTo(it) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}