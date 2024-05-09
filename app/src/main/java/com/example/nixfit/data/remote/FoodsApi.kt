package com.example.nixfit.data.remote

import com.example.nixfit.domain.model.Food

interface FoodsApi {
    suspend fun getFoods(): List<Food>

}