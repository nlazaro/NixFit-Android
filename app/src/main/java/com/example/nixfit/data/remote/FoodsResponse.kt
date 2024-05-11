package com.example.nixfit.data.remote

import com.google.gson.annotations.SerializedName

data class FoodsResponse(
    @SerializedName("product") val product: Product
)

data class Product(
    @SerializedName("product_name") val productName: String,
    @SerializedName("serving_size") val servingSize: String,
    @SerializedName("serving_quantity") val servingQuantity: String,
    @SerializedName("nutriments") val nutriments: Nutriments,
)

data class Nutriments(
    @SerializedName("carbohydrates") val carbohydrates: String,
    @SerializedName("protein") val protein: String,
    @SerializedName("fat") val fat: String,
    @SerializedName("energy") val energy: String,
    @SerializedName("sodium") val sodium: String,
    @SerializedName("sugar") val sugar: String
)