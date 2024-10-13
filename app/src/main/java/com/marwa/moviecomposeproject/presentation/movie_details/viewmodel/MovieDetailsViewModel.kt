package com.marwa.moviecomposeproject.presentation.movie_details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marwa.moviecomposeproject.core.di.ViewState
import com.marwa.moviecomposeproject.data.datasource.remote.network.NetworkStatus
import com.marwa.moviecomposeproject.data.model.CastResponse
import com.marwa.moviecomposeproject.domain.entity.MovieEntity
import com.marwa.moviecomposeproject.domain.usescase.GetMovieCastUseCase
import com.marwa.moviecomposeproject.domain.usescase.SaveMovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val saveMovieUseCase: SaveMovieUseCase,
    private val getMovieCastUseCase: GetMovieCastUseCase
) : ViewModel() {


    private var savedMovieState = MutableStateFlow(0L)
    val savedMovie: StateFlow<Long> = savedMovieState.asStateFlow()
    fun saveMovie(movieEntity: MovieEntity) {
        viewModelScope.launch {
            saveMovieUseCase.execute(movieEntity)
                .let { savedMovieState.value = it }
        }
    }


    private val _movieCast = MutableStateFlow<ViewState<CastResponse>>(ViewState.Empty())
    val movieCast: StateFlow<ViewState<CastResponse>> = _movieCast.asStateFlow()
    fun getMovieCast(movieId:Int) {
        viewModelScope.launch {
            getMovieCastUseCase.execute(movieId).collect {
                val state = when (it.status) {
                    NetworkStatus.Success -> ViewState.Loaded(it.data!!)
                    NetworkStatus.Failure -> ViewState.Error(it.error!!)
                    NetworkStatus.Loading -> ViewState.Loading()
                    else -> ViewState.Empty()
                }

                _movieCast.value = state
            }

        }
    }

    fun toggleBookmark(movieEntity: MovieEntity) {

    }

}