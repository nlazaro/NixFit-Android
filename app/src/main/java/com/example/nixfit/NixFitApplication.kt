@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.nixfit

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.nixfit.presentation.home.HomeScreen
import com.example.nixfit.ui.theme.NixFitTheme
import com.example.nixfit.util.NixFitTopAppBar


// enums that represent the different screens in the app
enum class NixFitScreen(@StringRes val title: Int) {
    Home(title = R.string.home_screen),
    Profile(title = R.string.profile_screen)
}

@Composable
fun NixFitApp(
    navController: NavHostController = rememberNavController()
) {
    // get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get current screen
    val currentScreen = NixFitScreen.valueOf(
        backStackEntry?.destination?.route ?: NixFitScreen.Home.name
    )
    Scaffold(
        topBar = {
            NixFitTopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = NixFitScreen.Home.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(it)
        ) {
            composable(route = NixFitScreen.Home.name) {
                HomeScreen(
                    onNextButtonClicked = { navController.navigate(NixFitScreen.Profile.name) }
                )
            }
        }
    }
}

@Preview
@Composable
fun NixFitAppPreview() {
    NixFitTheme {
        NixFitApp()
    }
}