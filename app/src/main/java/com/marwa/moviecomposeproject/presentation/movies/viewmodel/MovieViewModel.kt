package com.marwa.moviecomposeproject.presentation.movies.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marwa.moviecomposeproject.core.di.ViewState
import com.marwa.moviecomposeproject.data.datasource.remote.network.NetworkStatus
import com.marwa.moviecomposeproject.data.model.MovieResponse
import com.marwa.moviecomposeproject.domain.entity.MovieEntity
import com.marwa.moviecomposeproject.domain.usescase.GetNowShowingUseCase
import com.marwa.moviecomposeproject.domain.usescase.GetPopularUseCase
import com.marwa.moviecomposeproject.domain.usescase.SaveMovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    private val viewStateHandle: SavedStateHandle,
    private val nowShowingUseCase: GetNowShowingUseCase,
    private val popularUseCase: GetPopularUseCase,
    private val saveMovieUseCase: SaveMovieUseCase,

    ) : ViewModel() {
    init {
        getNowShowingMovies()
        getPopularMovies()
    }

    private val _nowShowingMovies = MutableStateFlow<ViewState<MovieResponse>>(ViewState.Empty())
    val nowShowingMovies: StateFlow<ViewState<MovieResponse>> = _nowShowingMovies.asStateFlow()
    private fun getNowShowingMovies() {
        viewModelScope.launch {
            nowShowingUseCase.execute().collect {
                val state = when (it.status) {
                    NetworkStatus.Success -> ViewState.Loaded(it.data!!)
                    NetworkStatus.Failure -> ViewState.Error(it.error!!)
                    NetworkStatus.Loading -> ViewState.Loading()
                    else -> ViewState.Empty()
                }

                _nowShowingMovies.value = state
            }

        }
    }


    private val _popularMovies = MutableStateFlow<ViewState<MovieResponse>>(ViewState.Empty())
    val popularMovies: StateFlow<ViewState<MovieResponse>> = _popularMovies.asStateFlow()
    private fun getPopularMovies() {
        viewModelScope.launch {
            popularUseCase.execute().collect {
                val state = when (it.status) {
                    NetworkStatus.Success -> ViewState.Loaded(it.data!!)
                    NetworkStatus.Failure -> ViewState.Error(it.error!!)
                    NetworkStatus.Loading -> ViewState.Loading()
                    else -> ViewState.Empty()
                }

                _popularMovies.value = state
            }
        }
    }


    private var savedMovieState = MutableStateFlow(0L)
    val savedMovie: StateFlow<Long> = savedMovieState.asStateFlow()
    fun saveMovie(movieEntity: MovieEntity) {
        viewModelScope.launch {
            saveMovieUseCase.execute(movieEntity)
                .let { savedMovieState.value = it }
        }
    }
}