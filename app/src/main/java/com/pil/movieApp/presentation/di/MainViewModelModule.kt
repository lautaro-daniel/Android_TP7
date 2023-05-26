package com.pil.movieApp.presentation.di

import com.pil.movieApp.presentation.mvvm.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MainViewModelModule {
    val mainViewModelModule = module {
        viewModel { MainViewModel() }
    }
}