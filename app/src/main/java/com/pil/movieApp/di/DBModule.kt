package com.pil.movieApp.di

import androidx.room.Room
import com.pil.movieApp.database.MovieRoomDataBase
import org.koin.dsl.module

object DBModule {
    private const val DB = "MovieDataBase"

    val dbModule = module {
        single { Room.databaseBuilder(get(), MovieRoomDataBase::class.java, DB).build() }
        single { get<MovieRoomDataBase>().moviesDao() }
    }
}