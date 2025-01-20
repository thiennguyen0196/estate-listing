package com.thiennguyen.estatelisting.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thiennguyen.estatelisting.data.local.dao.BookmarkedEstateDao
import com.thiennguyen.estatelisting.data.local.entity.BookmarkedEstateEntity

@Database(entities = [BookmarkedEstateEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookmarkedEstateDao(): BookmarkedEstateDao
}
