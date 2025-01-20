package com.thiennguyen.estatelisting.data.remote.response

import com.squareup.moshi.Json
import com.thiennguyen.estatelisting.domain.models.Localization
import com.thiennguyen.estatelisting.domain.models.LocalizationDetails

data class LocalizationResponse(
    @Json(name = "primary") val primary: String? = null,
    @Json(name = "de") val de: LocalizationDetailsResponse? = null
)

fun LocalizationResponse.toModel() = Localization(
    primary = primary.orEmpty(),
    de = de?.toModel()

)