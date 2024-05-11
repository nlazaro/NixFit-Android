package com.example.nixfit.domain.usecases

import com.example.nixfit.domain.repository.FoodsRepository

class FoodUseCases(
    private val foodsRepository: FoodsRepository
) {
    val getFoods = GetFoods(foodsRepository)
}