package com.example.nixfit.presentation.common

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
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
import com.example.nixfit.ui.theme.NixFitTheme
import kotlinx.coroutines.launch
import com.example.nixfit.presentation.common.BarcodeScannerUtil
import com.example.nixfit.presentation.settings.SettingsScreen

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
    var text by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }

    selectedItem = when (backStackState?.destination?.route) {
        Screen.Dashboard.route -> 0
        Screen.FoodDiary.route -> 1
        Screen.Recipe.route -> 2
        else -> 0
    }

    // Hides bottom navigation when user is on detail/settings screen
    val isBottomNavVisible = remember(key1 = backStackState){
        backStackState?.destination?.route == Screen.Dashboard.route ||
                backStackState?.destination?.route == Screen.FoodDiary.route ||
                backStackState?.destination?.route == Screen.Recipe.route
    }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    
    // main composable
    Scaffold(
        topBar = {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    ModalDrawerSheet {
                        Text("NixFit", modifier = Modifier.padding(16.dp))
                        HorizontalDivider()
                        NavigationDrawerItem(
                            label = { Text(text = "Settings") },
                            selected = false,
                            onClick = { navController.navigate(Screen.Settings.route) }
                        )
                        NavigationDrawerItem(
                            label = { Text(text = "About")},
                            selected = false,
                            onClick = { /*TODO*/ }
                        )
                    }
                }
            ){
                Box(
                    Modifier
                        .fillMaxSize()
                        .semantics { isTraversalGroup = true }
                ) {
                    SearchBar(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .semantics { traversalIndex = -1f },
                        inputField = {
                            SearchBarDefaults.InputField(
                                query = text,
                                onQueryChange = { text = it },
                                onSearch = { expanded = false },
                                expanded = expanded,
                                onExpandedChange = { expanded = it },
                                placeholder = { Text("Hinted search text") },
                                leadingIcon = {
                                    IconButton(
                                        onClick = {
                                            scope.launch{
                                                drawerState.open()
                                            }
                                        }
                                    ) {
                                        Icon(
                                            Icons.Default.Menu, contentDescription = null
                                        )
                                    }
                                },
                                trailingIcon = {
                                               IconButton(
                                                   onClick = {
                                                       BarcodeScannerUtil.startBarcodeScanning(context)
                                                   }
                                               ){
                                                   Icon(painterResource(R.drawable.barcode), contentDescription = "Barcode scanner")
                                               }
                                },
                            )
                        },
                        expanded = expanded,
                        onExpandedChange = { expanded = it },
                    ) {
                        repeat(4) { idx ->
                            val resultText = "Suggestion $idx"
                            ListItem(
                                headlineContent = { Text(resultText) },
                                supportingContent = { Text("Additional info") },
                                leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
                                colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                                modifier = Modifier
                                    .clickable {
                                        text = resultText
                                        expanded = false
                                    }
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 4.dp)
                            )
                        }
                    }

//                    LazyColumn(
//                        contentPadding = PaddingValues(start = 16.dp, top = 72.dp, end = 16.dp, bottom = 16.dp),
//                        verticalArrangement = Arrangement.spacedBy(8.dp)
//                    ) {
//                        val list = List(100) { "Text $it" }
//                        items(count = list.size) {
//                            Text(
//                                text = list[it],
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(horizontal = 16.dp),
//                            )
//                        }
//                    }
                }
            }
        },
        bottomBar = {
            if (isBottomNavVisible) {
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    NavigationBar (){
                        bottomNavItems.forEachIndexed { index, item ->
                            NavigationBarItem(
                                selected = selectedItem == index,
                                onClick = { navController.navigate(bottomNavItems[index].screen) },
                                icon = {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally){
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
    ){
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ){
            composable(Screen.Dashboard.route){
                DashboardScreen()
            }
            composable(Screen.FoodDiary.route) {
                FoodDiaryScreen()
            }
            composable(Screen.Recipe.route) {
                RecipeScreen()
            }
            composable(Screen.FoodInput.route){
                //FoodInputScreen()
            }
            composable(Screen.Settings.route){
                SettingsScreen()
            }
            composable(Screen.About.route){
                //AboutScreen()
            }
        }
    }
}



data class BottomNavItems(
    val title : String,
    val screen: String,
    @DrawableRes val selectedIcon : Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BottomPreview() {
    NixFitTheme(dynamicColor = false) {
        AppBars()
    }
}
