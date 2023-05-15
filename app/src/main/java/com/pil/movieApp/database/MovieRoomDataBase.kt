package com.pil.movieApp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pil.movieApp.database.dao.MovieDao
import com.pil.movieApp.database.entity.MovieEntity

@Database(
    entities = [
        MovieEntity::class,
    ],
    version = 1,
)
abstract class MovieRoomDataBase : RoomDatabase() {
    abstract fun moviesDao(): MovieDao
}
