package com.example.nixfit.domain.repository

import com.example.nixfit.domain.model.Product
import kotlinx.coroutines.flow.Flow

class GetFoods (
    private val foodsRepository: FoodsRepository
) {
    operator fun invoke(): Flow<List<Product>> {
        return foodsRepository.getFoods()
    }
}