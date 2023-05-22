package com.pil.movieApp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pil.movieApp.data.entity.MovieEntity

@Database(
    entities = [
        MovieEntity::class,
    ],
    version = 1
)

abstract class MovieDB : RoomDatabase() {
    abstract fun moviesDao(): MovieDao
}