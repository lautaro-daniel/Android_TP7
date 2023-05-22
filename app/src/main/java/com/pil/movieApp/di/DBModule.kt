package com.pil.movieApp.di

import androidx.room.Room
import com.pil.movieApp.database.MovieDataBase
import org.koin.dsl.module

object DBModule {
    private const val DB = "MovieDataBase"

    val dbModule = module {
        single { Room.databaseBuilder(get(), MovieDataBase::class.java, DB).build() }
        single { get<MovieDataBase>().moviesDao() }
    }
}