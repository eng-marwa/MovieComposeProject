package com.marwa.moviecomposeproject.domain.usescase

import com.marwa.moviecomposeproject.data.datasource.remote.network.NetworkResource
import com.marwa.moviecomposeproject.data.model.MovieResponse
import com.marwa.moviecomposeproject.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class GetPopularUseCase (private val movieRepository: IMovieRepository) {
    suspend fun execute(): Flow<NetworkResource<MovieResponse>> = movieRepository.getRemotePopularMovies()
}