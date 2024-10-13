package com.marwa.moviecomposeproject.domain.usescase

import com.marwa.moviecomposeproject.data.datasource.remote.network.NetworkResource
import com.marwa.moviecomposeproject.data.model.CastResponse
import com.marwa.moviecomposeproject.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieCastUseCase(private val movieRepository: IMovieRepository) {
    suspend fun execute(movieId: Int): Flow<NetworkResource<CastResponse>> =
        movieRepository.getMovieCast(movieId)
}