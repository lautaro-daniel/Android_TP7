package com.pil.movieApp.domain.usecase

import com.pil.movieApp.domain.database.MovieDataBase
import com.pil.movieApp.domain.entity.Movie
import com.pil.movieApp.domain.service.MovieService
import com.pil.movieApp.domain.util.CoroutineResult

interface GetMovieUseCase{
    operator fun invoke(movieId:String): CoroutineResult<List<Movie>>
}

class GetMoviesUserCaseImpl(
    private val movieService:MovieService,
    private val db: MovieDataBase
): GetMovieUseCase{
    override operator fun invoke(title:String): CoroutineResult<List<Movie>> {
        return when (val serviceResult = movieService.getMovies(title)){
            is CoroutineResult.Success -> {
                db.insertMovies(serviceResult.data)
                db.getAllMovies()
            }
            is CoroutineResult.Failure{
                db.getAllMovies()
            }
        }
    }
}