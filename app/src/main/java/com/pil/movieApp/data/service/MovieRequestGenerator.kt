package com.pil.movieApp.data.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRequestGenerator {
    private const val API_MOVIE_URL = "https://api.themoviedb.org"
    private const val accessToken =
        "eyJhbGciOiJIUzI1NiJ9." +
                "eyJhdWQiOiI3ZDNlNmE1YzQ1ZWJmNGZlYTJmYTQ3YmIwNDZmODc1NiIsInN1YiI6Ij" +
                "Y0NGVlYWMwMmZjY2VlMDJlNGNkNzE1ZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJ" +
                "zaW9uIjoxfQ." +
                "8O73pnC3L0twnDdZpcZQJpauecCSmY5Mb6Pr-_Nlewo"
    class OAuthInterceptor(private val tokenType: String, private val accessToken: String) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            request = request.newBuilder().header("Authorization", "$tokenType $accessToken").build()

            return chain.proceed(request)
        }
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(OAuthInterceptor("Bearer", accessToken))

    private val builder = Retrofit.Builder()
        .baseUrl(API_MOVIE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = builder.client(httpClient.build()).build()
        return retrofit.create(serviceClass)
    }


}
