package com.marwa.moviecomposeproject.core.routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marwa.moviecomposeproject.data.model.Movie
import com.marwa.moviecomposeproject.presentation.movie_details.view.MovieDetailsScreen
import com.marwa.moviecomposeproject.presentation.movies.view.MoviesScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun AppRouter(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.MOVIES) {
        composable(Routes.MOVIES) { backStackEntry ->
            MoviesScreen(
                modifier = modifier, onItemClick = { movie ->
                    val movieJson = Json.encodeToString(movie)
                    navController.currentBackStackEntry?.savedStateHandle?.set("movie", movieJson)
                    navController.navigate(Routes.MOVIE_DETAILS)
                }
            )
        }

        composable(
            route = Routes.MOVIE_DETAILS,
        ) {
            val movieJson = navController.previousBackStackEntry?.savedStateHandle?.get<String>("movie")
            val movie = movieJson?.let { Json.decodeFromString<Movie>(it) }
            movie?.let {
                MovieDetailsScreen(movie = it)
            } ?: run {
                // Handle the case where movie is null
                println("Error: movie is null")
            }
        }
    }
}
