package com.pil.movieApp.database

import com.pil.movieApp.database.dao.MovieDao
import com.pil.movieApp.database.mapper.mapToDataBaseExercise
import com.pil.movieApp.database.mapper.mapToLocalExercise
import com.pil.movieApp.service.model.Movie

interface MovieDataBase {
    suspend fun insertMovies(movies: List<Movie>)
    suspend fun getAllMovies(): List<Movie>
}

class MovieDataBaseImpl(private val movieDao: MovieDao) : MovieDataBase {

    override suspend fun insertMovies(movies: List<Movie>) {
        movies.forEach { exercise ->
            movieDao.insertMovie(exercise.mapToDataBaseExercise())
        }
    }

    override suspend fun getAllMovies(): List<Movie> {
        return movieDao.getPopularMovies().mapToLocalExercise()
    }
}
