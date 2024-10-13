package com.marwa.moviecomposeproject.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marwa.moviecomposeproject.domain.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: MovieEntity): Long

    @Query("select * from MovieEntity")
    suspend fun getMovies(): List<MovieEntity>
}