package com.marwa.moviecomposeproject.domain.usescase

import com.marwa.moviecomposeproject.domain.entity.MovieEntity
import com.marwa.moviecomposeproject.domain.repository.IMovieRepository

class SaveMovieUseCase(private val movieRepository: IMovieRepository) {
    suspend fun execute(movieEntity: MovieEntity):Long {
        println("SaveMovieUseCase: execute")
        return movieRepository.saveMovie(movieEntity)
    }
}