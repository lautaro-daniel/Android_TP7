package com.pil.movieApp.data.service

import com.pil.movieApp.data.service.api.MovieClient
import com.pil.movieApp.data.service.utils.transformToList
import com.pil.movieApp.domain.entity.Movie
import com.pil.movieApp.domain.service.MovieService
import com.pil.movieApp.domain.util.CoroutineResult

class MovieServiceImpl(private val api:MovieRequestGenerator):MovieService {
    override suspend fun getMovies(): CoroutineResult<List<Movie>> {
        try {
            val callResponse = api.createService(MovieClient::class.java).getPopularMovies()
            val response = callResponse.execute()
            if (response.isSuccessful){
                response.body()?.let{
                    return CoroutineResult.Success(it.transformToList())
                }
            }
        }catch (e : Exception){
            return CoroutineResult.Failure(e)
        }
        return CoroutineResult.Failure(java.lang.Exception())
    }
}