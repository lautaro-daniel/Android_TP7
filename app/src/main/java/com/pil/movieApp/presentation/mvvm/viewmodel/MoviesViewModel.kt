package com.pil.movieApp.presentation.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pil.movieApp.domain.entity.Movie
import com.pil.movieApp.domain.util.CoroutineResult
import com.pil.movieApp.presentation.mvvm.model.MoviesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(private val model: MoviesModel) : ViewModel() {

    private val mutableLiveData: MutableLiveData<MovieData> = MutableLiveData()
    fun getValue(): LiveData<MovieData> = mutableLiveData

    fun callService() = viewModelScope.launch {
        withContext(Dispatchers.IO) { model.getMovies() }.let { result ->
            when (result) {
                is CoroutineResult.Success -> {
                    mutableLiveData.value = MovieData(MovieStatus.SHOW_INFO, result.data)
                }
                is CoroutineResult.Failure -> {
                    mutableLiveData.value = MovieData(MovieStatus.ERROR, exception = result.exception)
                }
            }
        }
    }
    fun onBackButtonPressed() {
        mutableLiveData.postValue(MovieData(MovieStatus.BACK_BUTTON))
    }
    data class MovieData(
        val status: MovieStatus,
        val movies: List<Movie> = emptyList(),
        val exception: Exception? = null,
        )

    enum class MovieStatus {
        SHOW_INFO,
        ERROR,
        BACK_BUTTON
    }
}
