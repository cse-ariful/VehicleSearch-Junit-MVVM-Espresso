package com.nightcoder.vehiclesearch.di

import com.vehicleapp.domain.logger.ConsoleLogger
import com.vehicleapp.domain.logger.FileLogger
import com.vehicleapp.domain.logger.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import com.nightcoder.vehiclesearch.BuildConfig

@Module
@InstallIn(ActivityRetainedComponent::class)
class UtilityModule {

    @Provides
    fun provideLogger() : Logger {
        if(BuildConfig.DEBUG)return ConsoleLogger()
        return FileLogger()
    }
}