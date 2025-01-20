package com.thiennguyen.estatelisting.data.remote.response

import com.squareup.moshi.Json
import com.thiennguyen.estatelisting.domain.models.PriceDetails

data class PriceDetailsResponse(
    @Json(name = "area") val area: String? = null,
    @Json(name = "price") val price: Long? = null,
    @Json(name = "interval") val interval: String? = null
)

fun PriceDetailsResponse.toModel() = PriceDetails(
    area = area.orEmpty(),
    price = price ?: 0,
    interval = interval.orEmpty()
)