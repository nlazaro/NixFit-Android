package com.example.nixfit.data.repository

import com.example.nixfit.data.remote.FoodsApi
import com.example.nixfit.domain.repository.FoodsRepository

class FoodsRepositoryImpl (
    private val foodsApi: FoodsApi
): FoodsRepository {
    override fun getFoods(): Product {
        return foodsApi.getFoods()
    }
}
