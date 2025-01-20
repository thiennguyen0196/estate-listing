package com.thiennguyen.estatelisting.data.remote.response

import com.squareup.moshi.Json
import com.thiennguyen.estatelisting.domain.models.Address

data class AddressResponse(
    @Json(name = "locality") val locality: String? = null,
    @Json(name = "country") val country: String? = null,
    @Json(name = "region") val region: String? = null,
    @Json(name = "street") val street: String? = null,
    @Json(name = "postalCode") val postalCode: String? = null
)

fun AddressResponse.toModel() = Address(
    locality = locality.orEmpty(),
    country = country.orEmpty(),
    region = region.orEmpty(),
    street = street.orEmpty(),
    postalCode = postalCode.orEmpty()
)