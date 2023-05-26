package com.pil.movieApp.presentation

import android.app.Application
import com.pil.movieApp.di.ApiModule.apiModule
import com.pil.movieApp.di.DBModule.dbModule
import com.pil.movieApp.di.DataBaseModule.dataBaseModule
import com.pil.movieApp.di.ServiceModule.serviceModule
import com.pil.movieApp.di.UseCaseModule.useCaseModule
import com.pil.movieApp.presentation.di.MainViewModelModule.mainViewModelModule
import com.pil.movieApp.presentation.di.ModelModule.modelModule
import com.pil.movieApp.presentation.di.ViewModelModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class MoviesApplication: Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@MoviesApplication)

            modules(
                listOf(
                    mainViewModelModule,
                    viewModelModule,
                    serviceModule,
                    modelModule,
                    useCaseModule,
                    apiModule,
                    dbModule,
                    dataBaseModule
                )
            )
        }
    }
}