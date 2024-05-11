package com.example.nixfit.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoodsApi {
    @GET("product/{barcode}")
    suspend fun getFoods(
        @Path("barcode") barcode: Int,
        @Query("fields") fields: String
    ) : FoodsResponse
}