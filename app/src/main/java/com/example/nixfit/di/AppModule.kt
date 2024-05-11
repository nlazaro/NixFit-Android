package com.example.nixfit.di

import android.app.Application
import com.example.nixfit.data.manager.LocalUserManagerImpl
import com.example.nixfit.data.remote.FoodsApi
import com.example.nixfit.domain.manager.AppEntryUseCases
import com.example.nixfit.domain.manager.LocalUserManager
import com.example.nixfit.domain.manager.ReadAppEntry
import com.example.nixfit.domain.manager.SaveAppEntry
import com.example.nixfit.domain.repository.FoodsRepository
import com.example.nixfit.data.repository.FoodsRepositoryImpl
import com.example.nixfit.domain.manager.GetFoods
import com.example.nixfit.domain.repository.FoodUseCases
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
    fun provideLocalUserManager(
        application : Application
    ): LocalUserManager = LocalUserManagerImpl(application)
    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager : LocalUserManager
    ): AppEntryUseCases = AppEntryUseCases(
            readAppEntry = ReadAppEntry(localUserManager),
            saveAppEntry = SaveAppEntry(localUserManager)
    )

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
    fun providesFoodsRepository(
        api: FoodsApi
    ): FoodsRepository {
        return FoodsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesFoodsUseCases(
        foodsRepository: FoodsRepository
    ): FoodUseCases {
        return FoodUseCases(
            getFoods = GetFoods(foodsRepository)
        )
    }

}