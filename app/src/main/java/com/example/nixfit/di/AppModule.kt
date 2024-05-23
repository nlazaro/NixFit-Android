package com.example.nixfit.di

import android.app.Application
import androidx.room.Room
import com.example.nixfit.data.local.FoodDao
import com.example.nixfit.data.local.FoodDatabase
import com.example.nixfit.data.local.FoodTypeConverter
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

    @Provides
    @Singleton
    fun providesFoodDatabase(
        application: Application
    ): FoodDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = FoodDatabase::class.java,
            name = "food_db"
        ).addTypeConverter(FoodTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesFoodDao(
        foodDatabase: FoodDatabase
    ): FoodDao = foodDatabase.foodDao
}