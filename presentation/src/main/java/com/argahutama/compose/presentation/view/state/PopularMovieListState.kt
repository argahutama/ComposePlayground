package com.argahutama.compose.presentation.view.state

import com.argahutama.compose.domain.entity.MovieEntity

data class PopularMovieListState(
    val data: List<MovieEntity> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)