package com.example.nixfit.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nixfit.presentation.dashboard.DashboardScreen
import com.example.nixfit.presentation.nvgraph.Route
import com.example.nixfit.ui.theme.NixFitTheme

// Scaffold that contains top & bottom app bars
// Top: Hamburger icon, title, barcode scanner button
// Bottom: Dashboard, Food Log, and Recipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBars() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val navController = rememberNavController()
    val onBack: () -> Unit = {
        navController.popBackStack()
    }
    val screens = listOf(
        BottomNavigationItems(
            title = "Dashboard",
            selectionIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavigationItems(
            title = "Food Diary",
            selectionIcon = Icons.Filled.Email,
            unselectedIcon = Icons.Outlined.Email
        ),
        BottomNavigationItems(
            title = "Recipe",
            selectionIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings
        )
    )
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        Scaffold (
            bottomBar = {
                NavigationBar{

                }
            }
        )
    }
}

data class BottomNavigationItems(
    val title: String,
    val selectionIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BottomPreview() {
    NixFitTheme(dynamicColor = false) {
        AppBars()
    }
}