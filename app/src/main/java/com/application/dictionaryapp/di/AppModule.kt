package com.application.dictionaryapp.di

import com.application.dictionaryapp.helper.EndPoint
import com.application.dictionaryapp.networks.ApiDataSource
import com.application.dictionaryapp.networks.DictionaryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideRetrofit() : DictionaryService{
        return Retrofit.Builder()
            .baseUrl(EndPoint.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryService::class.java)
    }

    @Provides
    fun provideApiDataSource(service: DictionaryService) : ApiDataSource{
        return ApiDataSource(service)
    }
}