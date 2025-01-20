package com.thiennguyen.estatelisting.data.repositories

import com.thiennguyen.estatelisting.data.extension.flowTransform
import com.thiennguyen.estatelisting.data.local.dao.BookmarkedEstateDao
import com.thiennguyen.estatelisting.data.local.entity.BookmarkedEstateEntity
import com.thiennguyen.estatelisting.data.remote.response.toModel
import com.thiennguyen.estatelisting.data.services.ApiService
import com.thiennguyen.estatelisting.domain.models.Estate
import com.thiennguyen.estatelisting.domain.repositories.EstateRepository
import kotlinx.coroutines.flow.Flow

class EstateRepositoryImpl(
    private val apiService: ApiService,
    private val bookmarkedEstateDao: BookmarkedEstateDao,
) : EstateRepository {

    override fun getEstates(): Flow<List<Estate>> = flowTransform {
        apiService.getEstates().results?.map { it.toModel() }.orEmpty()
    }

    override fun getBookmarkedEstateIds(): Flow<List<String>> = flowTransform {
        bookmarkedEstateDao.getBookmarkedEstates().map { it.id }
    }

    override fun bookmarkEstate(id: String): Flow<Unit> = flowTransform {
        bookmarkedEstateDao.insertBookmarkedEstate(BookmarkedEstateEntity(id))
    }

    override fun removeBookmarkedEstate(id: String): Flow<Unit> = flowTransform {
        bookmarkedEstateDao.removeBookmarkedEstate(BookmarkedEstateEntity(id))
    }
}
