package com.argahutama.compose.data.repository

import com.argahutama.compose.data.source.remote.MovieService
import com.argahutama.compose.data.util.mapper.mapMovieDtoToEntity
import com.argahutama.compose.domain.repository.MovieRepository
import com.argahutama.compose.domain.util.tryParse
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService
) : MovieRepository {
    override suspend fun getNowPlayingMovies() =
        movieService.getNowPlayingMovies().tryParse { response ->
            val movieDtoList = response?.results.orEmpty()
            movieDtoList.map(::mapMovieDtoToEntity)
        }

    override suspend fun getMovieDetails(id: String) =
        movieService.getMovieDetails(id).tryParse { movieDto ->
            mapMovieDtoToEntity(movieDto)
        }
}