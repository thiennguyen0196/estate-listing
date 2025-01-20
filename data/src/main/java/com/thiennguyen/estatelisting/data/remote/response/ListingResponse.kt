package com.thiennguyen.estatelisting.data.remote.response

import com.squareup.moshi.Json
import com.thiennguyen.estatelisting.domain.models.Listing

data class ListingResponse(
    @Json(name = "id") val id: String? = null,
    @Json(name = "offerType") val offerType: String? = null,
    @Json(name = "categories") val categories: List<String>? = null,
    @Json(name = "prices") val prices: PricesResponse? = null,
    @Json(name = "address") val address: AddressResponse? = null,
    @Json(name = "localization") val localization: LocalizationResponse? = null,
)

fun ListingResponse.toModel() = Listing(
    id = id.orEmpty(),
    offerType = offerType.orEmpty(),
    categories = categories.orEmpty(),
    prices = prices?.toModel(),
    address = address?.toModel(),
    localization = localization?.toModel()
)