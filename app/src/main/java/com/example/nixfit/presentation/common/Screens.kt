package com.example.nixfit.presentation.common

import androidx.annotation.StringRes
import com.example.nixfit.R

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int
){
    object Dashboard : Screen("dashboard", R.string.dashboard)
    object FoodDiary : Screen("foodDiary", R.string.fooddiary)
    object Recipe : Screen("recipe", R.string.recipe)
}
