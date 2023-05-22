package com.pil.movieApp.data.database

import com.pil.movieApp.data.service.utils.toMovieDB
import com.pil.movieApp.data.service.utils.toMovieList
import com.pil.movieApp.domain.database.MovieDataBase
import com.pil.movieApp.domain.entity.Movie
import com.pil.movieApp.domain.util.CoroutineResult
import java.lang.Exception

class MovieDataBaseImpl(private val movieDao: MovieDao): MovieDataBase {
    override suspend fun insertMovies(movieList: List<Movie>) {
        movieList.forEach {
            movieDao.insertMovie(it.toMovieDB())
        }

    }

    override suspend fun getAllMovies(): CoroutineResult<List<Movie>> =
        movieDao.getPopularMovies().let {
            if(it.isNotEmpty()){
                CoroutineResult.Success(it.toMovieList())
            }else{
                CoroutineResult.Failure(Exception())
            }
        }
}