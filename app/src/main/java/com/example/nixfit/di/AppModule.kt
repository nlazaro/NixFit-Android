package com.example.nixfit.di

import com.example.nixfit.data.remote.FoodsApi
import com.example.nixfit.util.Constants.BASE_URL
import com.google.android.datatransport.runtime.dagger.Module
import com.google.android.datatransport.runtime.dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideFoodsApi() : FoodsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoodsApi::class.java)
    }
}