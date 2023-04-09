package com.nightcoder.vehiclesearch.di

import com.nightcoder.vehiclesearch.logger.ConsoleLogger
import com.nightcoder.vehiclesearch.logger.FileLogger
import com.nightcoder.vehiclesearch.logger.Logger
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