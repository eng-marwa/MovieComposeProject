package com.marwa.moviecomposeproject.domain.repository.impl

import com.marwa.moviecomposeproject.data.datasource.local.MovieDao
import com.marwa.moviecomposeproject.data.datasource.remote.interfaces.IMovieRemoteDS
import com.marwa.moviecomposeproject.data.datasource.remote.network.NetworkResource
import com.marwa.moviecomposeproject.data.model.CastResponse
import com.marwa.moviecomposeproject.data.model.MovieResponse
import com.marwa.moviecomposeproject.domain.entity.MovieEntity
import com.marwa.moviecomposeproject.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(private val remoteDS: IMovieRemoteDS, private val movieDao: MovieDao) :
    IMovieRepository {
    override suspend fun getRemoteNowShowingMovies(): Flow<NetworkResource<MovieResponse>> =
        remoteDS.getNowShowingMovies()

    override suspend fun getRemotePopularMovies(): Flow<NetworkResource<MovieResponse>> =
        remoteDS.getPopularMovies()

    override suspend fun getLocalMovies(): Flow<MovieEntity> =
        flow { movieDao.getMovies() }


    override suspend fun saveMovie(movieEntity: MovieEntity): Long =
        movieDao.insertMovie(movieEntity)

    override suspend fun getMovieCast(movieId: Int): Flow<NetworkResource<CastResponse>> = remoteDS.getMovieCast(movieId)


}