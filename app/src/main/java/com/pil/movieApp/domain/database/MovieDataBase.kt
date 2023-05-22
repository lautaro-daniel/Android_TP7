package com.pil.movieApp.domain.database

import com.pil.movieApp.domain.entity.Movie
import com.pil.movieApp.domain.util.CoroutineResult

interface MovieDataBase {
    suspend fun insertMovies(movieList: List<Movie>)
    suspend fun getAllMovies(): CoroutineResult<List<Movie>>
}