package com.argahutama.compose.data.model

import com.google.gson.annotations.SerializedName

data class GetNowPlayingMoviesResponse(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val results: List<MovieDto>?,
)