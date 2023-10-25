package com.argahutama.compose.data.model

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id") val id: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("genre_ids") val genreIds: List<String>?,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("popularity") val popularity: Double?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("vote_count") val voteCount: Double?,
    @SerializedName("vote_average") val voteAverage: Double?,
)