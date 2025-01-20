package com.thiennguyen.estatelisting.data.remote.response

import com.squareup.moshi.Json
import com.thiennguyen.estatelisting.domain.models.Prices

data class PricesResponse(
    @Json(name = "currency") val currency: String? = null,
    @Json(name = "buy") val buy: PriceDetailsResponse? = null
)

fun PricesResponse.toModel() = Prices(
    currency = currency.orEmpty(),
    buy = buy?.toModel(),
)