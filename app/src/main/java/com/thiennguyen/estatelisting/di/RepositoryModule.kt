package com.thiennguyen.estatelisting.di

import com.thiennguyen.estatelisting.data.local.dao.BookmarkedEstateDao
import com.thiennguyen.estatelisting.data.repositories.EstateRepositoryImpl
import com.thiennguyen.estatelisting.data.services.ApiService
import com.thiennguyen.estatelisting.domain.repositories.EstateRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideRepository(
        apiService: ApiService,
        bookmarkedEstateDao: BookmarkedEstateDao
    ): EstateRepository = EstateRepositoryImpl(apiService, bookmarkedEstateDao)
}
