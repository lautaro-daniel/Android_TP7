package com.pil.movieApp.mvvm.model

import com.pil.movieApp.database.MovieDataBase
import com.pil.movieApp.mvvm.contract.MainContract
import com.pil.movieApp.service.MovieService
import com.pil.movieApp.service.model.Movie
import com.pil.movieApp.util.CoroutineResult

class MainModel(
    private val service: MovieService,
    private val database: MovieDataBase,
) : MainContract.Model {

    override suspend fun getMovies(): CoroutineResult<List<Movie>> {
        return when (val movies = service.getMovies()) {
            is CoroutineResult.Success -> {
                database.insertMovies(movies.data.movies)
                CoroutineResult.Success(database.getAllMovies())
            }

            is CoroutineResult.Failure -> {
                CoroutineResult.Success(database.getAllMovies())
            }
        }
    }
}
