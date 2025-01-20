package com.thiennguyen.estatelisting.ui.screens.list

import com.thiennguyen.estatelisting.ui.base.BaseViewModel
import com.thiennguyen.estatelisting.models.EstateUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EstateListingViewModel @Inject constructor() : BaseViewModel() {

    private val _estates = MutableStateFlow<List<EstateUiModel>>(emptyList())
    val estates = _estates.asStateFlow()

    @Suppress("MaxLineLength")
    suspend fun getEstates() {
        showLoading()
        delay(1000L)
        hideLoading()
        _estates.value = listOf(
            EstateUiModel(
                id = "id1",
                imageUrl = "https://media2.homegate.ch/listings/heia/104123262/image/6b53db714891bfe2321cc3a6d4af76e1.jpg",
                title = "title1",
                price = "price1",
                address = "address1",
                isBookmarked = false,
            ),
            EstateUiModel(
                id = "id2",
                imageUrl = "https://media2.homegate.ch/listings/heia/104123262/image/328c41c0c0805299f5c28d680fbac4d9.jpg",
                title = "title2",
                price = "price2",
                address = "address2",
                isBookmarked = true,
            ),
            EstateUiModel(
                id = "id3",
                imageUrl = "https://media2.homegate.ch/listings/heia/104123262/image/2333f298be7cc3609daaaf2e39e91bf9.jpg",
                title = "title3",
                price = "price3",
                address = "address3",
                isBookmarked = true,
            ),
            EstateUiModel(
                id = "id4",
                imageUrl = "https://media2.homegate.ch/listings/heia/104123262/image/8944c80cb8afb8d5d579ca4faf7dbbb4.jp",
                title = "title4",
                price = "price4",
                address = "address4",
                isBookmarked = true,
            ),
            EstateUiModel(
                id = "id5",
                imageUrl = "https://media2.homegate.ch/listings/heia/104123262/image/bbb5b2fb8a1cf58ce690e0cfca23d266.jpg",
                title = "title5",
                price = "price5",
                address = "address5",
                isBookmarked = true,
            ),
        )
    }

    fun updateBookmarkedItem(item: EstateUiModel) {
        val list = _estates.value.toMutableList()
        val editedItemIndex = list.indexOfFirst {
            it.id == item.id
        }
        if (editedItemIndex != -1) {
            list[editedItemIndex] = item.copy(isBookmarked = !item.isBookmarked)
        }
        _estates.value = list
    }
}