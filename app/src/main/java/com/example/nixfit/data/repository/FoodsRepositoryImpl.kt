package com.example.nixfit.data.repository

import arrow.core.Either
import com.example.nixfit.data.remote.FoodsApi
import com.example.nixfit.domain.model.Food
import javax.inject.Inject

//class FoodsRepositoryImpl @Inject constructor(
//    private val foodsApi: FoodsApi
//): FoodsRepository {
//    override suspend fun getFoods(): Either<Throwable, List<Food>> {
//        return Either.Right(foodsApi.getFoods())
//    }
//}