package com.pil.movieApp.di

import com.pil.movieApp.domain.service.MovieService
import com.pil.movieApp.service.MovieServiceImpl
import org.koin.dsl.module

object ServiceModule {
    val serviceModule = module {
        factory<MovieService> { MovieServiceImpl(get()) }
    }
}