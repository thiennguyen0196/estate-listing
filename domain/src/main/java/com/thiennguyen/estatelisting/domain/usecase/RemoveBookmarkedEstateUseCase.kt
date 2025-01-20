package com.thiennguyen.estatelisting.domain.usecase

import com.thiennguyen.estatelisting.domain.repositories.EstateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveBookmarkedEstateUseCase @Inject constructor(private val repository: EstateRepository) {

    operator fun invoke(id: String): Flow<Unit> {
        return repository.removeBookmarkedEstate(id)
    }
}