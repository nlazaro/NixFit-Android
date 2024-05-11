package com.example.nixfit.domain.model

data class Product(
    val nutriments: Nutriments,
    val productName: String,
    val servingQuantity: String,
    val servingSize: String
)