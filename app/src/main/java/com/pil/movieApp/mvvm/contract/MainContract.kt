package com.pil.movieApp.mvvm.contract

import androidx.lifecycle.LiveData
import com.pil.movieApp.presentation.mvvm.viewmodel.MoviesViewModel
import com.pil.movieApp.service.model.Movie
import com.pil.movieApp.domain.util.CoroutineResult
import kotlinx.coroutines.Job

interface MainContract {

    interface Model {
        suspend fun getMovies(): CoroutineResult<List<Movie>>
    }

    interface ViewModel {
        fun getValue(): LiveData<MoviesViewModel.MainData>
        fun callService(): Job
    }
}
