package com.example.nixfit.domain.manager

import com.example.nixfit.domain.model.Nutriments
import com.example.nixfit.domain.model.Product
import com.example.nixfit.domain.repository.FoodsRepository
import kotlinx.coroutines.flow.Flow

class GetFoods (
    private val foodsRepository : FoodsRepository
){
    suspend operator fun invoke(nutriments: List<String>) : Flow<List<Product>>{
        return foodsRepository.getFoods(nutriments)
    }
}