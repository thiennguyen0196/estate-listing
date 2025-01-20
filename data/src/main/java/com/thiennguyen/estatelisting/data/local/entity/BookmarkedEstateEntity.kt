package com.thiennguyen.estatelisting.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarked_estates")
data class BookmarkedEstateEntity(
    @PrimaryKey val id: String,
)