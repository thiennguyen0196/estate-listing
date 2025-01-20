package com.thiennguyen.estatelisting.data.remote.response

import com.squareup.moshi.Json
import com.thiennguyen.estatelisting.domain.models.Text

data class TextResponse(
    @Json(name = "title") val title: String? = null
)

fun TextResponse.toModel() = Text(
    title = title.orEmpty()
)