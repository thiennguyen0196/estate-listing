package com.thiennguyen.estatelisting.ui.screens.list

import androidx.lifecycle.viewModelScope
import com.thiennguyen.estatelisting.domain.usecase.BookmarkEstateUseCase
import com.thiennguyen.estatelisting.domain.usecase.GetBookmarkedEstateIdsUseCase
import com.thiennguyen.estatelisting.domain.usecase.GetEstateListingUseCase
import com.thiennguyen.estatelisting.domain.usecase.RemoveBookmarkedEstateUseCase
import com.thiennguyen.estatelisting.models.EstateUiModel
import com.thiennguyen.estatelisting.models.toUiModel
import com.thiennguyen.estatelisting.ui.base.BaseViewModel
import com.thiennguyen.estatelisting.utils.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class EstateListingViewModel @Inject constructor(
    private val dispatchersProvider: DispatchersProvider,
    private val getEstateListingUseCase: GetEstateListingUseCase,
    private val getBookmarkedEstateIdsUseCase: GetBookmarkedEstateIdsUseCase,
    private val bookmarkEstateUseCase: BookmarkEstateUseCase,
    private val removeBookmarkedEstateUseCase: RemoveBookmarkedEstateUseCase,
) : BaseViewModel() {

    private val _estates = MutableStateFlow<List<EstateUiModel>>(emptyList())
    val estates = _estates.asStateFlow()

    init {
        getEstates()
    }

    fun updateBookmarkedItem(item: EstateUiModel) {
        val updatedValue = !item.isBookmarked
        val currentList = _estates.value.toMutableList()
        val editedItemIndex = currentList.indexOfFirst {
            it.id == item.id
        }
        if (editedItemIndex != -1) {
            currentList[editedItemIndex] = item.copy(isBookmarked = !item.isBookmarked)
        }
        updateBookmarkEstate(
            item.id,
            updatedValue
        )
            .injectLoading()
            .onEach {
                _estates.value = currentList
            }
            .flowOn(dispatchersProvider.io)
            .catch { e -> _error.emit(e) }
            .launchIn(viewModelScope)
    }

    private fun getEstates() {
        var bookmarkedEstateIds = emptyList<String>()
        getBookmarkedEstateIdsUseCase.invoke()
            .flatMapLatest {
                bookmarkedEstateIds = it
                getEstateListingUseCase.invoke()
            }
            .injectLoading()
            .onEach {
                _estates.value = it.map { estate ->
                    estate.toUiModel(bookmarkedEstateIds.contains(estate.id))
                }
            }
            .flowOn(dispatchersProvider.io)
            .catch { e -> _error.emit(e) }
            .launchIn(viewModelScope)
    }

    private fun updateBookmarkEstate(
        id: String,
        updatedValue: Boolean
    ): Flow<Unit> {
        return if (updatedValue) {
            bookmarkEstateUseCase.invoke(id)
        } else {
            removeBookmarkedEstateUseCase.invoke(id)
        }
    }
}