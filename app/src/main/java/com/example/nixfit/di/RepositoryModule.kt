package com.example.nixfit.di

import com.example.nixfit.data.repository.FoodsRepositoryImpl
import com.example.nixfit.domain.repository.FoodsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindFoodsRepository(
        foodsRepositoryImpl: FoodsRepositoryImpl
    ): FoodsRepository
}