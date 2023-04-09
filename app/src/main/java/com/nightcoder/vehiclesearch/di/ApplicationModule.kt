package com.nightcoder.vehiclesearch.di

import com.nightcoder.vehiclesearch.BuildConfig
import com.nightcoder.vehiclesearch.data.api.VehicleSearchApi
import com.nightcoder.vehiclesearch.domain.service.SearchVehicleService
import com.nightcoder.vehiclesearch.data.vehicleSearch.SearchVehicleServiceImpl
import com.nightcoder.vehiclesearch.domain.usecases.SearchVehicleInfoUseCase
import com.nightcoder.vehiclesearch.logger.ConsoleLogger
import com.nightcoder.vehiclesearch.logger.FileLogger
import com.nightcoder.vehiclesearch.logger.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import javax.inject.Qualifier

@Module
@InstallIn(ActivityRetainedComponent::class)
object ApplicationModule {

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

    @Provides
    fun provideLogger() : Logger{
        if(BuildConfig.DEBUG)return ConsoleLogger()
        return FileLogger()
    }

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher