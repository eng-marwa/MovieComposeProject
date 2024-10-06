package com.marwa.moviecomposeproject.domain.repository.impl

import com.marwa.moviecomposeproject.data.datasource.remote.interfaces.IMovieRemoteDS
import com.marwa.moviecomposeproject.data.datasource.remote.network.NetworkResource
import com.marwa.moviecomposeproject.data.model.MovieResponse
import com.marwa.moviecomposeproject.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(private val remoteDS: IMovieRemoteDS) : IMovieRepository {
    override suspend fun getRemoteNowShowingMovies(): Flow<NetworkResource<MovieResponse>>  = remoteDS.getNowShowingMovies()
    override suspend fun getRemotePopularMovies(): Flow<NetworkResource<MovieResponse>> = remoteDS.getPopularMovies()

//    override suspend fun getLocalNowShowingMovies(): Flow<MovieEntity> {
//       //room db
//    }
}