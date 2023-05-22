package com.pil.movieApp.presentation.di

import com.pil.movieApp.presentation.mvvm.model.MoviesModel
import org.koin.dsl.module

object ModelModule {
    val modelModule = module{
        factory{ MoviesModel(get()) }
    }
}