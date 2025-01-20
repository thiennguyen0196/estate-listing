package com.thiennguyen.estatelisting.data.remote.response

import com.squareup.moshi.Json
import com.thiennguyen.estatelisting.domain.models.Estate

data class EstateResponse(
    @Json(name = "id") val id: String? = null,
    @Json(name = "remoteViewing") val remoteViewing: Boolean? = null,
    @Json(name = "listing") val listing: ListingResponse? = null
)

fun EstateResponse.toModel() = Estate(
    id = id.orEmpty(),
    remoteViewing = remoteViewing ?: false,
    listing = listing?.toModel(),
)