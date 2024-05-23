package com.example.nixfit.presentation.common

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nixfit.R
import com.example.nixfit.presentation.dashboard.DashboardScreen
import com.example.nixfit.presentation.fooddiary.FoodDiaryScreen
import com.example.nixfit.presentation.navigation.Screen
import com.example.nixfit.presentation.recipe.RecipeScreen
import com.example.nixfit.presentation.settings.SettingsScreen
import com.example.nixfit.ui.theme.NixFitTheme
import kotlinx.coroutines.launch

// Scaffold that contains top & bottom app bars
// Top: Hamburger icon, title, barcode scanner button
// Bottom: Dashboard, Food Log, and Recipe

@ExperimentalMaterial3Api
@Composable
fun AppBars() {
    val bottomNavItems = listOf(
        BottomNavItems("Dashboard", "dashboard", R.drawable.dashboard),
        BottomNavItems("Food Log", "foodDiary", R.drawable.food_diary),
        BottomNavItems("Recipe", "recipe", R.drawable.food_recipe)
    )
    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    selectedItem = when (backStackState?.destination?.route) {
        Screen.Dashboard.route -> 0
        Screen.FoodDiary.route -> 1
        Screen.Recipe.route -> 2
        else -> 0
    }

    // Hides bottom navigation when user is on detail/settings screen
    val isBottomNavVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Screen.Dashboard.route ||
                backStackState?.destination?.route == Screen.FoodDiary.route ||
                backStackState?.destination?.route == Screen.Recipe.route
    }
    ///
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                //Spacer(modifier = Modifier.height(16.dp))
                Text("NixFit", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                NavigationDrawerItem(
                    label = { Text(text = "Settings") },
                    selected = false,
                    onClick = {
                        navController.navigate(Screen.Settings.route)
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                NavigationDrawerItem(
                    label = { Text(text = "About") },
                    selected = false,
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("NixFit") },
                    scrollBehavior = scrollBehavior,
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(
                                Icons.Default.Menu, contentDescription = null
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                BarcodeScannerUtil.startBarcodeScanning(context)
                            }) {
                            Icon(
                                painter = painterResource(id = R.drawable.barcode),
                                contentDescription = "barcode scanner button"
                            )
                        }
                    }
                )
            },
            bottomBar = {
                if (isBottomNavVisible) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        NavigationBar {
                            bottomNavItems.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    selected = selectedItem == index,
                                    onClick = { navController.navigate(bottomNavItems[index].screen) },
                                    icon = {
                                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                            Icon(
                                                painter = painterResource(id = item.selectedIcon),
                                                contentDescription = null
                                            )
                                            Text(
                                                text = item.title,
                                                style = MaterialTheme.typography.labelSmall
                                            )
                                        }
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = MaterialTheme.colorScheme.primary,
                                        selectedTextColor = MaterialTheme.colorScheme.primary,
                                        indicatorColor = MaterialTheme.colorScheme.background
                                    ),
                                )
                            }
                        }
                    }
                }
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.Dashboard.route,
                modifier = Modifier.padding(bottom = it.calculateBottomPadding())
            ) {
                composable(Screen.Dashboard.route) {
                    DashboardScreen()
                }
                composable(Screen.FoodDiary.route) {
                    FoodDiaryScreen()
                }
                composable(Screen.Recipe.route) {
                    RecipeScreen()
                }
                composable(Screen.FoodInput.route) {
                    //FoodInputScreen()
                }
                composable(Screen.Settings.route) {
                    SettingsScreen()
                }
                composable(Screen.About.route) {
                    //AboutScreen()
                }
            }
        }
    }
}


data class BottomNavItems(
    val title: String,
    val screen: String,
    @DrawableRes val selectedIcon: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BottomPreview() {
    NixFitTheme(dynamicColor = false) {
        AppBars()
    }
}
