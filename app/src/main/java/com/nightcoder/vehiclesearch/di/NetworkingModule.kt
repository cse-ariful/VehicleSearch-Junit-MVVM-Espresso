package com.nightcoder.vehiclesearch.di

import com.nightcoder.vehiclesearch.BuildConfig
import com.vehicleapp.data.networking.RequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class NetworkingModule {

    @Provides
    fun provideRequestInterceptor():Interceptor{
        return RequestInterceptor(apiKey = BuildConfig.SEARCH_API_KEY)
    }
    @Provides
    fun provideOkHttpClient(interceptor: Interceptor):OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/") //This is a mock server url running on local pc using mockoon (https://mockoon.com/)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()



    }
}
