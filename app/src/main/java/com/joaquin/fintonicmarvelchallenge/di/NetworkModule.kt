package com.joaquin.fintonicmarvelchallenge.di

import com.joaquin.fintonicmarvelchallenge.data.network.MarvelApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /*
        Only one instance of Retrofit
     */
    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com:443/v1/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMarvelApiClient(retrofit: Retrofit) : MarvelApiClient {
        return retrofit.create(MarvelApiClient::class.java)
    }
}