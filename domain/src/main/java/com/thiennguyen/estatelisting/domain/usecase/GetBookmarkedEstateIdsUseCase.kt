package com.thiennguyen.estatelisting.domain.usecase

import com.thiennguyen.estatelisting.domain.repositories.EstateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarkedEstateIdsUseCase @Inject constructor(private val repository: EstateRepository) {

    operator fun invoke(): Flow<List<String>> {
        return repository.getBookmarkedEstateIds()
    }
}