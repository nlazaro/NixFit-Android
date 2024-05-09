package com.example.nixfit.di

import com.example.nixfit.data.repository.FoodsRepositoryImpl
import com.example.nixfit.domain.repository.FoodsRepository
import com.google.android.datatransport.runtime.dagger.Binds
import com.google.android.datatransport.runtime.dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



//@InstallIn(SingletonComponent::class)
//@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindFoodsRepository(impl: FoodsRepositoryImpl): FoodsRepository

}