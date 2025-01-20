package com.thiennguyen.estatelisting.di

import android.content.Context
import androidx.room.Room
import com.thiennguyen.estatelisting.data.local.dao.BookmarkedEstateDao
import com.thiennguyen.estatelisting.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBookmarkedEstateDao(database: AppDatabase): BookmarkedEstateDao {
        return database.bookmarkedEstateDao()
    }
}