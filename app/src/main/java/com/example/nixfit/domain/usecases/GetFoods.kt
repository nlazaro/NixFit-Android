package com.example.nixfit.domain.usecases

import com.example.nixfit.data.remote.FoodsResponse
import com.example.nixfit.domain.repository.FoodsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class GetFoods (
    private val foodsRepository : FoodsRepository
){
    operator fun invoke(barcode: Int, fields: String) : Flow<Result<FoodsResponse>> = flow {
        try {
            val foodsResponse = foodsRepository.getFoods(barcode, fields)
            emit (Result.success(foodsResponse))
        } catch (e: Exception){
            emit(Result.failure(e))
        } catch (e: HttpException){
            emit(Result.failure(e))
        }
    }
}