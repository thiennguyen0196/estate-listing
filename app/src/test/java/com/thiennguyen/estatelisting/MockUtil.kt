package com.thiennguyen.estatelisting

import com.thiennguyen.estatelisting.domain.models.Estate

object MockUtil {

    val estates = listOf(
        Estate(
            id = "id",
            remoteViewing = false,
            listing = null,
        )
    )

    val ids = listOf("id")
}
