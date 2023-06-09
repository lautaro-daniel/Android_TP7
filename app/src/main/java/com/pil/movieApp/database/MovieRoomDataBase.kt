package com.pil.movieApp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pil.movieApp.data.database.MovieDao
import com.pil.movieApp.data.entity.MovieEntity

@Database(
    entities = [
        MovieEntity::class,
    ],
    version = 1,
)
abstract class MovieRoomDataBase : RoomDatabase() {
    abstract fun moviesDao(): MovieDao
}
