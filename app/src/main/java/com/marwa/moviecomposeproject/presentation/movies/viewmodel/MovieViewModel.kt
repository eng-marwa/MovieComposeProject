package com.marwa.moviecomposeproject.presentation.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marwa.moviecomposeproject.core.di.ViewState
import com.marwa.moviecomposeproject.data.datasource.remote.network.NetworkStatus
import com.marwa.moviecomposeproject.data.model.MovieResponse
import com.marwa.moviecomposeproject.domain.usescase.GetNowShowingUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val useCase: GetNowShowingUseCase) : ViewModel() {
    init {
        getNowShowingMovies()
    }

    private val _nowShowingMovies = MutableStateFlow<ViewState<MovieResponse>>(ViewState.Empty())
    val nowShowingMovies: StateFlow<ViewState<MovieResponse>> = _nowShowingMovies
    fun getNowShowingMovies() {
        viewModelScope.launch {
            useCase.execute().collect {
                val state = when (it.status) {
                    NetworkStatus.Success -> ViewState.Loaded(it.data!!)
                    NetworkStatus.Failure -> ViewState.Error(it.error!!)
                    else -> ViewState.Empty()
                }

                _nowShowingMovies.value = state
            }
        }
    }
}