package com.pil.movieApp.service

import com.pil.movieApp.service.model.MovieList
import retrofit2.Call
import retrofit2.http.GET

interface MovieClient {
    @GET("/3/movie/popular")
    fun getPopularMovies(): Call<MovieList>
}
