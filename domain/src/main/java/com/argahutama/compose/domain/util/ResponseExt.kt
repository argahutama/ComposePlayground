package com.argahutama.compose.domain.util

import retrofit2.Response

fun <T, R> Response<T>.tryParse(mapper: (T?) -> R) = try {
    val body = body()

    if (isSuccessful) {
        ResourceState.Success(data = mapper(body))
    } else {
        ResourceState.Error(
            statusCode = code(),
            message = message(),
            data = mapper(body)
        )
    }
} catch (e: Exception) {
    ResourceState.Error(message = e.localizedMessage)
}