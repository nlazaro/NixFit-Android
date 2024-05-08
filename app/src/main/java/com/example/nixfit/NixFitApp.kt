@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.nixfit

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.nixfit.ui.home.HomeScreen
import com.example.nixfit.ui.theme.NixFitTheme
import javax.sql.DataSource


// enums that represent the different screens in the app
enum class NixFitScreen(@StringRes val title: Int) {
    Home(title = R.string.home_screen),
    Profile(title = R.string.profile_screen)
}

// Composable that displays the topBar and displays back button if back navigation is possible.
@Composable
fun NixFitTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    currentScreen: NixFitScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(stringResource(currentScreen.title))
                },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun NixFitApp(
    navController: NavHostController = rememberNavController(),
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = NixFitScreen.valueOf(
        backStackEntry?.destination?.route ?: NixFitScreen.Home.name
    )
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            NixFitTopAppBar(
               currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp()},
                scrollBehavior = scrollBehavior
            )
        }
    ){
        innerPadding ->
        val modifier = Modifier.padding(innerPadding)
    }
}

@Preview
@Composable
fun NixFitAppPreview() {
    NixFitTheme {
        NixFitApp()
    }
}
