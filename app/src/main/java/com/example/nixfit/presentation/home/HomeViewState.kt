package com.example.nixfit.presentation.home

data class HomeViewState (
    val foods: List<Food> = emptyList(),
    val error: String? = null
)
