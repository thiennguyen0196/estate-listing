package com.thiennguyen.estatelisting.models

import androidx.compose.ui.util.fastJoinToString
import com.thiennguyen.estatelisting.domain.models.Address
import com.thiennguyen.estatelisting.domain.models.Estate
import com.thiennguyen.estatelisting.domain.models.Prices

data class EstateUiModel(
    val id: String,
    val imageUrl: String,
    val title: String,
    val price: String,
    val address: String,
    var isBookmarked: Boolean,
)

fun Estate.toUiModel(bookmarked: Boolean) = EstateUiModel(
    id = id,
    imageUrl = listing?.localization?.de?.attachments?.firstOrNull()?.url.orEmpty(),
    title = listing?.localization?.de?.text?.title.orEmpty(),
    price = listing?.prices?.getFullPrice().orEmpty(),
    address = listing?.address?.getFullAddress().orEmpty(),
    isBookmarked = bookmarked
)

fun Address.getFullAddress(): String {
    return listOf(street, locality)
        .filter { it.isNotBlank() }
        .fastJoinToString()

}

fun Prices.getFullPrice(): String {
    if (buy?.price == null || buy?.price == 0L) {
        return ""
    }
    return listOf(buy!!.price.toString(), currency)
        .filter { it.isNotBlank() }
        .fastJoinToString(separator = " ")

}