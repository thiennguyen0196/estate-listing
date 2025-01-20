package com.thiennguyen.estatelisting.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thiennguyen.estatelisting.data.local.entity.BookmarkedEstateEntity

@Dao
interface BookmarkedEstateDao {

    @Query("SELECT * FROM bookmarked_estates")
    suspend fun getBookmarkedEstates(): List<BookmarkedEstateEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmarkedEstate(estate: BookmarkedEstateEntity)

    @Delete
    suspend fun removeBookmarkedEstate(estate: BookmarkedEstateEntity)
}
