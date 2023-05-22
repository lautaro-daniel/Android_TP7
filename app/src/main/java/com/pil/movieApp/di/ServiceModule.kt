package com.pil.movieApp.di

import com.pil.movieApp.data.service.MovieServiceImpl
import com.pil.movieApp.domain.service.MovieService
import org.koin.dsl.module

object ServiceModule {
    val serviceModule = module {
        factory<MovieService> { MovieServiceImpl(get()) }
    }
}