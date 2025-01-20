package com.thiennguyen.estatelisting.di

import com.thiennguyen.estatelisting.utils.DispatchersProvider
import com.thiennguyen.estatelisting.utils.DispatchersProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideDispatchersProvider(): DispatchersProvider {
        return DispatchersProviderImpl()
    }
}