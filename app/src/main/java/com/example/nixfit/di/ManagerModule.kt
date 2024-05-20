package com.example.nixfit.di

import com.example.nixfit.data.manager.LocalUserManagerImpl
import com.example.nixfit.domain.manager.LocalUserManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ManagerModule {
    @Binds
    @Singleton
    abstract fun bindLocalUserManager(
        localUserManagerImpl: LocalUserManagerImpl
    ): LocalUserManager
}