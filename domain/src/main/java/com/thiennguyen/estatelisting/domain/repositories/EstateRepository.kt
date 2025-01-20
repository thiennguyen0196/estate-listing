package com.thiennguyen.estatelisting.domain.repositories

import com.thiennguyen.estatelisting.domain.models.Estate
import kotlinx.coroutines.flow.Flow

interface EstateRepository {

    fun getEstates(): Flow<List<Estate>>

    fun getBookmarkedEstateIds(): Flow<List<String>>

    fun bookmarkEstate(id: String): Flow<Unit>

    fun removeBookmarkedEstate(id: String): Flow<Unit>
}
