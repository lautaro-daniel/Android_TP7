package com.pil.movieApp.mvvm.contract

import androidx.lifecycle.LiveData
import com.pil.movieApp.mvvm.viewmodel.MainViewModel
import com.pil.movieApp.service.model.Movie
import com.pil.movieApp.util.CoroutineResult
import kotlinx.coroutines.Job

interface MainContract {

    interface Model {
        suspend fun getMovies(): CoroutineResult<List<Movie>>
    }

    interface ViewModel {
        fun getValue(): LiveData<MainViewModel.MainData>
        fun callService(): Job
    }
}
