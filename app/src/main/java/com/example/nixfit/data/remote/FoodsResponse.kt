package com.example.nixfit.data.remote

import com.example.nixfit.domain.model.Product

data class FoodResponse(
    val code: String,
    val product: Product,
    val status: Int
)