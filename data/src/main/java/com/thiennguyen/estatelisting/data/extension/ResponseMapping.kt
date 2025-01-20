package com.thiennguyen.estatelisting.data.extension

import com.thiennguyen.estatelisting.domain.exceptions.ApiException
import com.thiennguyen.estatelisting.domain.exceptions.NoConnectivityException
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.UnknownHostException
import kotlin.experimental.ExperimentalTypeInference

@OptIn(ExperimentalTypeInference::class)
fun <T> flowTransform(@BuilderInference block: suspend FlowCollector<T>.() -> T) = flow {
    runCatching { block() }
        .onSuccess { result -> emit(result) }
        .onFailure { exception -> throw exception.mapError() }
}

private fun Throwable.mapError(): Throwable {
    return when (this) {
        is UnknownHostException,
        is InterruptedIOException -> NoConnectivityException

        is HttpException -> {
            ApiException(
                code(),
                message()
            )
        }

        else -> this
    }
}
