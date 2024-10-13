package com.marwa.moviecomposeproject.domain.usescase

import com.marwa.moviecomposeproject.domain.repository.IMovieRepository

class GetSavedPopularUseCase(private val movieRepository: IMovieRepository) {
    suspend fun execute() = movieRepository.getLocalMovies()
}