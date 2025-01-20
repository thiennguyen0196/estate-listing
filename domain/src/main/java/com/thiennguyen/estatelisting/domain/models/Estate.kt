package com.thiennguyen.estatelisting.domain.models

data class Estate(
    val id: String,
    val remoteViewing: Boolean,
    val listing: Listing?
)