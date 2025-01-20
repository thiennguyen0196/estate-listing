package com.thiennguyen.estatelisting.data.remote.response

import com.squareup.moshi.Json
import com.thiennguyen.estatelisting.domain.models.Attachment

data class AttachmentResponse(
    @Json(name = "type") val type: String? = null,
    @Json(name = "url") val url: String? = null,
    @Json(name = "file") val file: String? = null
)

fun AttachmentResponse.toModel() = Attachment(
    type = type.orEmpty(),
    url = url.orEmpty(),
    file = file.orEmpty()
)