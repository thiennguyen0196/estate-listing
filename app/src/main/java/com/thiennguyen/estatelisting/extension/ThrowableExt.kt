package com.thiennguyen.estatelisting.extension

import android.content.Context
import android.widget.Toast
import com.thiennguyen.estatelisting.R
import com.thiennguyen.estatelisting.domain.exceptions.NoConnectivityException

fun Throwable.userReadableMessage(context: Context): String {
    return when (this) {
        is NoConnectivityException -> context.getString(R.string.error_no_connectivity)
        else -> message
    } ?: context.getString(R.string.error_generic)
}

fun Throwable.showToast(context: Context) = context.showToast(userReadableMessage(context))
