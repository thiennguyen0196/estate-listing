package com.thiennguyen.estatelisting.di

import com.squareup.moshi.Moshi
import com.thiennguyen.estatelisting.data.remote.providers.MoshiBuilderProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MoshiModule {

    @Provides
    fun provideMoshi(): Moshi = MoshiBuilderProvider.moshiBuilder.build()
}
