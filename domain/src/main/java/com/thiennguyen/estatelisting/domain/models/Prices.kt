package com.thiennguyen.estatelisting.domain.models

data class Prices(
    val currency: String,
    val buy: PriceDetails?
)