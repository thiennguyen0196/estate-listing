package com.thiennguyen.estatelisting.domain.exceptions

object NoConnectivityException : RuntimeException()

data class ApiException(
    val httpCode: Int,
    val httpMessage: String?
) : RuntimeException()