package com.pil.movieApp.presentation.mvvm.model

import com.pil.movieApp.domain.entity.Movie
import com.pil.movieApp.domain.util.CoroutineResult

class MoviesModel(private val getMoviesUserCase: GetMoviesUserCase) {
    suspend fun getMovies(): CoroutineResult<List<Movie>> = getMoviesUserCase()
}