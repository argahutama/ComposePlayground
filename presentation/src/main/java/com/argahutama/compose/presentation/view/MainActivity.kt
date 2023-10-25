package com.argahutama.compose.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.argahutama.compose.common.theme.MyApplicationTheme
import com.argahutama.compose.common.theme.route.Screen
import com.argahutama.compose.presentation.view.page.AuthorPage
import com.argahutama.compose.presentation.view.page.MovieDetailsPage
import com.argahutama.compose.presentation.view.page.NowPlayingMovieListPage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.NowPlayingMovieList.route,
                    ) {
                        composable(
                            route = Screen.NowPlayingMovieList.route
                        ) {
                            NowPlayingMovieListPage(
                                navigateToDetail = {
                                    navController.navigate(Screen.MovieDetails.createRoute(it))
                                },
                                navigateToAuthor = {
                                    navController.navigate(Screen.Author.route)
                                }
                            )
                        }
                        composable(
                            route = Screen.MovieDetails.route,
                            arguments = listOf(
                                navArgument("id") {
                                    type = NavType.StringType
                                }
                            ),
                        ) {
                            val id = it.arguments?.getString("id").orEmpty()
                            MovieDetailsPage(id)
                        }
                        composable(
                            route = Screen.Author.route
                        ) {
                            AuthorPage()
                        }
                    }
                }
            }
        }
    }
}