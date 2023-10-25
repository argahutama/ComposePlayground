package com.argahutama.compose.common.theme.route

sealed class Screen(val route: String) {
    object NowPlayingMovieList : Screen("now-playing-movie-list")
    object MovieDetails : Screen("movie-details/{id}") {
        fun createRoute(id: String) = "movie-details/$id"
    }
    object Author : Screen("author")
}