package com.marwa.moviecomposeproject.domain.usescase

import com.marwa.moviecomposeproject.domain.repository.IMovieRepository

class GetSavedNowShowingUseCase(private val movieRepository: IMovieRepository) {
    suspend fun execute() = movieRepository.getLocalMovies()
}