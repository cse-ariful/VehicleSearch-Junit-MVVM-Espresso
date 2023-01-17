package com.nightcoder.greenleaf.di

import com.nightcoder.greenleaf.data.api.VehicleSearchApi
import com.nightcoder.greenleaf.data.vehicleSearch.SearchVehicleService
import com.nightcoder.greenleaf.data.vehicleSearch.SearchVehicleServiceImpl
import com.nightcoder.greenleaf.domain.usecases.SearchVehicleInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://da4705d6-9b2b-4f2a-8f19-6db21183fd13.mock.pstmn.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Provides
    fun providerVehicleSearchApi(retrofit: Retrofit): VehicleSearchApi {
        return retrofit.create(VehicleSearchApi::class.java)
    }

    @Provides
    fun providerVehicleSearchService(api: VehicleSearchApi): SearchVehicleService {
        return SearchVehicleServiceImpl(api)
    }

    @Provides
    fun providerSearchVehicleInfoUseCase(service: SearchVehicleService): SearchVehicleInfoUseCase {
        return SearchVehicleInfoUseCase(service)
    }

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher