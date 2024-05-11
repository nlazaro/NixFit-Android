package com.example.nixfit.data.remote

import com.example.nixfit.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodsApi {
    @GET("product")
    suspend fun getFoods(
        @Query("product_name") productName: String,
        @Query("nutriments") nutriments: String,
        @Query("serving_size") servingSize: String,
        @Query("serving_quantity") servingQuantity: String,
    ) : FoodResponse

}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(Constants.OPENFOODFACTS_API_URL)
    .build()