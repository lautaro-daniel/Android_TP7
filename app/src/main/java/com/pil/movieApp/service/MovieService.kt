package com.pil.movieApp.service

import com.pil.movieApp.service.model.MovieList
import com.pil.movieApp.util.CoroutineResult

interface MovieService {
    suspend fun getMovies(): CoroutineResult<MovieList>
}

class MovieServiceImpl(private val client: MovieClient) : MovieService {

    override suspend fun getMovies(): CoroutineResult<MovieList> {
        try {
            val response = client.getPopularMovies().execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    return CoroutineResult.Success(it)
                }
            }
            return CoroutineResult.Failure(Exception(response.errorBody().toString()))
        } catch (e: Exception) {
            return CoroutineResult.Failure(e)
        }
    }
}
