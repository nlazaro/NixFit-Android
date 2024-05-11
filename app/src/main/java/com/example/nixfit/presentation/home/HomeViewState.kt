package com.example.nixfit.presentation.home

data class HomeViewState (
    val foods: List<Product> = emptyList(),
    val error: String? = null
)
