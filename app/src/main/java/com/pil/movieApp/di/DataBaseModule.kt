package com.pil.movieApp.di

import com.pil.movieApp.data.database.MovieDataBaseImpl
import com.pil.movieApp.domain.database.MovieDataBase
import org.koin.dsl.module

object DataBaseModule {
    val dataBaseModule = module {
        factory<MovieDataBase> { MovieDataBaseImpl(get()) }
    }
}