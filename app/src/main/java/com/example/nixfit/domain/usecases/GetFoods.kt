package com.example.nixfit.domain.usecases

import com.example.nixfit.domain.model.Product
import com.example.nixfit.domain.repository.FoodsRepository
class GetFoods (
    private val foodsRepository : FoodsRepository
){
    suspend operator fun invoke() : Product {
        return foodsRepository.getFoods()
    }
}