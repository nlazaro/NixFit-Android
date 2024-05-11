package com.example.nixfit.domain.repository

import com.example.nixfit.data.remote.FoodsResponse

interface FoodsRepository {
    suspend fun getFoods(barcode: Int, fields: String) : FoodsResponse
}