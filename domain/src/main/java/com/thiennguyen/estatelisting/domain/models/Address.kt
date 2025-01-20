package com.thiennguyen.estatelisting.domain.models

data class Address(
    val locality: String,
    val country: String,
    val region: String?,
    val street: String,
    val postalCode: String
)