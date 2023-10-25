package com.argahutama.compose.domain.util

sealed class ResourceState<T> {
    data class Success<T>(val data: T) : ResourceState<T>()
    data class Error<T>(
        val statusCode: Int? = 403,
        val message: String? = "Something went wrong",
        val data: T? = null,
    ) : ResourceState<T>()
}