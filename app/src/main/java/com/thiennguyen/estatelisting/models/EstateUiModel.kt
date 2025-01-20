package com.thiennguyen.estatelisting.models

data class EstateUiModel(
    val id: String,
    val imageUrl: String,
    val title: String,
    val price: String,
    val address: String,
    var isBookmarked: Boolean,
)