package com.marwa.moviecomposeproject.core.routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.marwa.moviecomposeproject.data.model.Movie
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
                modifier = modifier,
                onItemClick = { movie ->
                    val movieJson = Json.encodeToString(movie)
                    savedStateHandle["movie"] = movieJson
                    navController.currentBackStackEntry?.arguments?.putString("movie", movieJson)
                    navController.navigate("${Routes.MOVIES}/${movie.id}")

                },
            )
        }
        composable(
            Routes.MOVIE_DETAILS,
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieJson = backStackEntry.arguments?.getString("movie")
            val movie = movieJson?.let { Json.decodeFromString<Movie>(it) }
            MovieDetailsScreen(movie!!)

        }
    }
}