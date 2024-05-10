package com.example.nixfit.di

import android.app.Application
import com.example.nixfit.data.manager.LocalUserManagerImpl
import com.example.nixfit.domain.manager.AppEntryUseCases
import com.example.nixfit.domain.manager.LocalUserManager
import com.example.nixfit.domain.manager.ReadAppEntry
import com.example.nixfit.domain.manager.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}

//    fun provideFoodsApi() : FoodsApi {
//        return Retrofit.Builder()
//            .baseUrl(OPENFOODFACTS_API_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(FoodsApi::class.java)