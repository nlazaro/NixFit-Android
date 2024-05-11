package com.example.nixfit.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.nixfit.data.remote.FoodsApi
import com.example.nixfit.domain.repository.FoodsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodsRepositoryImpl(
    private val foodsApi: FoodsApi
): FoodsRepository {
//    override fun getFoods() {
//        return null
//    }
}
