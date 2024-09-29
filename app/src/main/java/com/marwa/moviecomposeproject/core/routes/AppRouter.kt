package com.marwa.moviecomposeproject.core.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marwa.moviecomposeproject.presentation.movies.screens.MoviesScreen

@Composable
fun AppRouter() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.MOVIES) {
        composable(Routes.MOVIES) {
            MoviesScreen()
        }
    }
}