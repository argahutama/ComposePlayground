package com.argahutama.compose.presentation.view.state

import com.argahutama.compose.domain.entity.MovieEntity

data class MovieDetailsState(
    val data: MovieEntity? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)