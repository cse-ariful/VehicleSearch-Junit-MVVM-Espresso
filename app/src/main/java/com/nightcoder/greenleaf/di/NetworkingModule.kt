package com.nightcoder.greenleaf.di

import com.nightcoder.greenleaf.data.networking.RequestInterceptor
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
            .baseUrl("https://da4705d6-9b2b-4f2a-8f19-6db21183fd13.mock.pstmn.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp)
            .build()

    }
}
