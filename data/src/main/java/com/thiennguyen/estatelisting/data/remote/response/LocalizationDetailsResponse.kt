package com.thiennguyen.estatelisting.data.remote.response

import com.squareup.moshi.Json
import com.thiennguyen.estatelisting.domain.models.LocalizationDetails

data class LocalizationDetailsResponse(
    @Json(name = "attachments") val attachments: List<AttachmentResponse>? = null,
    @Json(name = "text") val text: TextResponse? = null,
)

fun LocalizationDetailsResponse.toModel() = LocalizationDetails(
    attachments = attachments?.map { it.toModel() }.orEmpty(),
    text = text?.toModel()
)