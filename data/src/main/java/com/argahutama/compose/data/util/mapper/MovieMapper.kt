package com.argahutama.compose.data.util.mapper

import com.argahutama.compose.data.model.MovieDto
import com.argahutama.compose.domain.entity.MovieEntity

fun mapMovieDtoToEntity(movieDto: MovieDto?) =
    if (movieDto == null) MovieEntity()
    else MovieEntity(
        id = movieDto.id.orEmpty(),
        backdropPath = movieDto.backdropPath.orEmpty(),
        posterPath = "https://image.tmdb.org/t/p/w500${movieDto.posterPath.orEmpty()}",
        genreIds = movieDto.genreIds.orEmpty(),
        originalTitle = movieDto.originalTitle.orEmpty(),
        title = movieDto.title.orEmpty(),
        overview = movieDto.overview.orEmpty(),
        popularity = movieDto.popularity ?: .0,
        releaseDate = movieDto.releaseDate.orEmpty(),
        voteCount = movieDto.voteCount ?: .0,
        voteAverage = movieDto.voteAverage ?: .0,
    )