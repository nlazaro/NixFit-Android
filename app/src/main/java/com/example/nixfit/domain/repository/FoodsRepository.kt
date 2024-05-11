package com.example.nixfit.domain.repository

import androidx.paging.PagingData
import com.example.nixfit.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface FoodsRepository {
    suspend fun getFoods() : Flow<List<Product>>
}