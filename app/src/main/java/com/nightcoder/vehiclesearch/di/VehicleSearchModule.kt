package com.nightcoder.vehiclesearch.di

import com.vehicleapp.data.api.VehicleSearchApi
import com.vehicleapp.data.vehicleSearch.SearchVehicleServiceImpl
import com.vehicleapp.domain.service.SearchVehicleService
import com.vehicleapp.domain.usecases.SearchVehicleInfoUseCase
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