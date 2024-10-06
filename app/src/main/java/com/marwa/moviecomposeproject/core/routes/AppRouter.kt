package com.marwa.moviecomposeproject.core.routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marwa.moviecomposeproject.data.model.MovieData
import com.marwa.moviecomposeproject.presentation.movie_details.MovieDetailsScreen
import com.marwa.moviecomposeproject.presentation.movies.screens.MoviesScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun AppRouter(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.MOVIES) {
        composable(Routes.MOVIES) { backStackEntry ->
            val savedStateHandle = backStackEntry.savedStateHandle
            MoviesScreen(
                modifier = modifier, onItemClick = { movie ->
                    val data =
                        MovieData(movie.id, movie.title, movie.posterPath, movie.backdropPath)
                    val movieJson = Json.encodeToString(data)
                    println("movieJson: $movieJson")
                    navController.currentBackStackEntry?.arguments?.putString("movie", movieJson)
                    navController.navigate(Routes.MOVIE_DETAILS)
                }, viewStateHandle = savedStateHandle
            )
        }

        composable(Routes.MOVIE_DETAILS) { backStackEntry ->
            val movieJson: String? = backStackEntry.arguments?.getString("movie")
            val movie = movieJson?.let { Json.decodeFromString<MovieData>(it) }
            movie?.let {
                MovieDetailsScreen(movie = it)
            }
        }
    }
}