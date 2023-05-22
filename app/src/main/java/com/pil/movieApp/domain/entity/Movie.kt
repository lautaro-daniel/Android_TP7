package com.pil.movieApp.domain.entity

data class Movie(
    val title: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val originalLanguage: String,
    val voteAverage: Double,
    val voteCount: Int,
)