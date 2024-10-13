package com.marwa.moviecomposeproject.domain.repository

import com.marwa.moviecomposeproject.data.datasource.remote.network.NetworkResource
import com.marwa.moviecomposeproject.data.model.CastResponse
import com.marwa.moviecomposeproject.data.model.MovieResponse
import com.marwa.moviecomposeproject.domain.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    suspend fun getRemoteNowShowingMovies(): Flow<NetworkResource<MovieResponse>>
    suspend fun getRemotePopularMovies(): Flow<NetworkResource<MovieResponse>>
    suspend fun getLocalMovies(): Flow<MovieEntity>
    suspend fun saveMovie(movieEntity: MovieEntity): Long
    suspend fun getMovieCast(movieId: Int): Flow<NetworkResource<CastResponse>>

}