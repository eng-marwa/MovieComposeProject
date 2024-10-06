package com.marwa.moviecomposeproject.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marwa.moviecomposeproject.domain.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDb : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private var INSTANCE: MovieDb? = null
        fun getDatabase(context: Context): MovieDb {
            return INSTANCE ?: synchronized(this) {
                val instance = buildMovieDb(context)
                INSTANCE = instance
                INSTANCE!!
            }
        }

        private fun buildMovieDb(context: Context) =
            Room.databaseBuilder(context.applicationContext, MovieDb::class.java, "movie_db")
                .fallbackToDestructiveMigration().build()
    }

}