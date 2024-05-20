package com.example.nixfit.di

import com.example.nixfit.data.remote.FoodsApi
import com.example.nixfit.domain.repository.FoodsRepository
import com.example.nixfit.domain.usecases.FoodsUseCases
import com.example.nixfit.domain.usecases.GetFoods
import com.example.nixfit.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesFoodsApi(): FoodsApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.OPENFOODFACTS_API_URL)
            .build()
            .create(FoodsApi::class.java)
    }

    @Provides
    @Singleton
    fun providesFoodsUseCases(
        foodsRepository: FoodsRepository
    ): FoodsUseCases {
        return FoodsUseCases(
            getFoods = GetFoods(foodsRepository))
    }
}