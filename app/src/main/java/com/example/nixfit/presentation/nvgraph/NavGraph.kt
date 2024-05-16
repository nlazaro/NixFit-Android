package com.example.nixfit.presentation.nvgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.nixfit.presentation.common.MainNavigator
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
            composable(route = Route.OnBoardingScreen.route){
                val viewModel : OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(event = viewModel::onEvent)
            }
        }
        navigation(
            route = Route.AppMainNavigation.route,
            startDestination = Route.DashboardScreen.route
        ){
            composable(route = Route.DashboardScreen.route){
                MainNavigator()
            }
        }
    }
}

//        composable<OnBoardingScreen>{
//            val viewModel : OnBoardingViewModel = hiltViewModel()
//            OnBoardingScreen(
//                event = viewModel::onEvent
//            )
//        }
//        composable<DashboardScreen>{
//            DashboardScreen()
//        }
//        composable<FoodLogScreen>{
//            // TODO
//        }
//        composable<RecipeScreen>{
//            // TODO
//        }
//        composable<SearchScreen> {
//            // TODO
//        }
//        composable<FoodSummaryScreen> {
//            // TODO
//        }
//        composable<SettingsScreen> {
//            // TODO
//        }
//        composable<ProfileScreen> {
//            // TODO
//        }
//    }
//}
//
//@Serializable
//object OnBoardingScreen
//@Serializable
//object DashboardScreen
//@Serializable
//object FoodLogScreen
//@Serializable
//object RecipeScreen
//@Serializable
//object SearchScreen
//@Serializable
//object FoodSummaryScreen
//@Serializable
//object SettingsScreen
//@Serializable
//object ProfileScreen
