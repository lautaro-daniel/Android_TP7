package com.pil.movieApp.domain.usecase

import com.pil.movieApp.domain.database.MovieDataBase
import com.pil.movieApp.domain.entity.Movie
import com.pil.movieApp.domain.service.MovieService
import com.pil.movieApp.domain.util.CoroutineResult

interface GetMovieUseCase{
    suspend operator fun invoke(): CoroutineResult<List<Movie>>
}
class GetMoviesUserCaseImpl(
    private val movieService:MovieService,
    private val db: MovieDataBase
): GetMovieUseCase{
    override suspend operator fun invoke(): CoroutineResult<List<Movie>> {
        return when (val serviceResult = movieService.getMovies()){
            is CoroutineResult.Success -> {
                db.insertMovies(serviceResult.data)
                db.getAllMovies()
            }
            is CoroutineResult.Failure -> {
                db.getAllMovies()
            }
        }
    }
}