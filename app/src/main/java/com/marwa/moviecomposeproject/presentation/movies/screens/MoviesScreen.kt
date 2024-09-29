package com.marwa.moviecomposeproject.presentation.movies.screens

import androidx.compose.runtime.Composable
import com.marwa.moviecomposeproject.presentation.movies.viewmodel.MovieViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun MoviesScreen() {
    val viewModel: MovieViewModel = getViewModel()
    val state = viewModel.nowShowingMovies
}