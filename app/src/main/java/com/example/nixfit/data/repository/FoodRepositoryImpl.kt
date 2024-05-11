package com.example.nixfit.data.repository

import com.example.nixfit.data.remote.FoodsApi
import com.example.nixfit.data.remote.FoodsResponse
import com.example.nixfit.domain.repository.FoodsRepository

class FoodsRepositoryImpl (
    private val foodsApi: FoodsApi
) : FoodsRepository {
    override suspend fun getFoods(barcode : Int, fields: String) : FoodsResponse {
        return foodsApi.getFoods(barcode, fields)
    }
}