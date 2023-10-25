package com.argahutama.compose.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    val id: String = "",
    val backdropPath: String = "",
    val posterPath: String = "",
    val genreIds: List<String> = emptyList(),
    val originalTitle: String = "",
    val title: String = "",
    val overview: String = "",
    val popularity: Double = .0,
    val releaseDate: String = "",
    val voteCount: Double = .0,
    val voteAverage: Double = .0,
) : Parcelable