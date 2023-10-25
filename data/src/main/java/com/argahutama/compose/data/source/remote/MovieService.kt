package com.argahutama.compose.data.source.remote

import com.argahutama.compose.data.model.GetNowPlayingMoviesResponse
import com.argahutama.compose.data.model.MovieDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

private const val API_KEY = "2174d146bb9c0eab47529b2e77d6b526"

interface MovieService {
    @GET("movie/now_playing?api_key=$API_KEY")
    suspend fun getNowPlayingMovies(): Response<GetNowPlayingMoviesResponse>

    @GET("movie/{id}?api_key=$API_KEY")
    suspend fun getMovieDetails(@Path("id") id: String): Response<MovieDto>
}