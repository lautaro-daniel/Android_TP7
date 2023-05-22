package com.pil.movieApp.presentation.mvvm.model

import com.pil.movieApp.domain.entity.Movie
import com.pil.movieApp.domain.usecase.GetMovieUseCase
import com.pil.movieApp.domain.util.CoroutineResult

class MoviesModel(private val getMoviesUserCase: GetMovieUseCase) {
    suspend fun getMovies(): CoroutineResult<List<Movie>> = getMoviesUserCase()
}