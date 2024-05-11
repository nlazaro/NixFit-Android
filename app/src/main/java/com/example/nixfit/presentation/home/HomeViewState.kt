package com.example.nixfit.presentation.home

import com.example.nixfit.domain.model.Product

data class HomeViewState (
    val foods: List<Product> = emptyList(),
    val error: String? = null
)
