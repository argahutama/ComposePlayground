package com.argahutama.compose.domain.repository

import com.argahutama.compose.domain.entity.MovieEntity
import com.argahutama.compose.domain.util.ResourceState

interface MovieRepository {
    suspend fun getNowPlayingMovies(): ResourceState<List<MovieEntity>>
    suspend fun getMovieDetails(id: String): ResourceState<MovieEntity>
}