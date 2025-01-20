package com.thiennguyen.estatelisting.data.remote.response

import com.squareup.moshi.Json

data class EstatesResponse(
    @Json(name = "from") val from: Int? = null,
    @Json(name = "size") val size: Int? = null,
    @Json(name = "total") val total: Int? = null,
    @Json(name = "results") val results: List<EstateResponse>? = null,
    @Json(name = "maxFrom") val maxFrom: Int? = null
)