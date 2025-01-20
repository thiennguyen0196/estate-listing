package com.thiennguyen.estatelisting.domain.models

data class Listing(
    val id: String,
    val offerType: String,
    val categories: List<String>,
    val prices: Prices?,
    val address: Address?,
    val localization: Localization?,
)