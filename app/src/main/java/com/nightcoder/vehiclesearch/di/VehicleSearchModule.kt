package com.nightcoder.vehiclesearch.di

import com.nightcoder.vehiclesearch.data.api.VehicleSearchApi
import com.nightcoder.vehiclesearch.data.vehicleSearch.SearchVehicleServiceImpl
import com.nightcoder.vehiclesearch.domain.service.SearchVehicleService
import com.nightcoder.vehiclesearch.domain.usecases.SearchVehicleInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
class VehicleSearchModule {

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
}