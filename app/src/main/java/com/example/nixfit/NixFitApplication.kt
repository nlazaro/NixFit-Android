@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.nixfit

import android.app.Application
import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import dagger.hilt.android.HiltAndroidApp


// enums that represent the different screens in the app
enum class NixFitScreen(@StringRes val title: Int) {
    Home(title = R.string.home_screen),
    Profile(title = R.string.profile_screen)
}

@HiltAndroidApp
class NixFitApplication : Application()
//fun NixFitApp(
//    navController: NavHostController = rememberNavController()
//) {
//    // get current back stack entry
//    val backStackEntry by navController.currentBackStackEntryAsState()
//    // Get current screen
//    val currentScreen = NixFitScreen.valueOf(
//        backStackEntry?.destination?.route ?: NixFitScreen.Home.name
//    )
//    Scaffold(
//        topBar = {
//            NixFitTopAppBar(
//                currentScreen = currentScreen,
//                canNavigateBack = navController.previousBackStackEntry != null,
//                navigateUp = { navController.navigateUp() }
//            )
//        }
//    ) {
//        NavHost(
//            navController = navController,
//            startDestination = NixFitScreen.Home.name,
//            modifier = Modifier
//                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
//                .padding(it)
//        ) {
//            composable(route = NixFitScreen.Home.name) {
////                HomeScreen(
////                    onNextButtonClicked = { navController.navigate(NixFitScreen.Profile.name) }
////                )
//            }
//        }
//
//        BarcodeScanner()
//    }
//}
