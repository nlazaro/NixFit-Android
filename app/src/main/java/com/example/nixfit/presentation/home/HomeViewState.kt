package com.example.nixfit.presentation.home

import com.example.nixfit.domain.model.Food

data class HomeViewState (
    val foods: List<Food> = emptyList(),
    val error: String? = null
)
