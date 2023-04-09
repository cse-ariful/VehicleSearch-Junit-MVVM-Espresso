package com.nightcoder.vehiclesearch.di

import com.nightcoder.vehiclesearch.data.networking.RequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class NetworkingModule {
    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(RequestInterceptor())
        .build()

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.0.101:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp)
            .build()

    }
}
