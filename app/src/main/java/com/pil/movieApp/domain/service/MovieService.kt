package com.pil.movieApp.domain.service

import com.pil.movieApp.domain.entity.Movie
import com.pil.movieApp.domain.util.CoroutineResult

interface MovieService {
    suspend fun getMovies(): CoroutineResult<List<Movie>>
}