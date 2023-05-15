package com.pil.movieApp.database.mapper

import com.pil.movieApp.database.entity.MovieEntity
import com.pil.movieApp.service.model.Movie

fun Movie.mapToDataBaseExercise(): MovieEntity =
    MovieEntity(
        title = title,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        originalLanguage = originalLanguage,
        voteAverage = voteAverage,
        voteCount = voteCount
    )

fun List<MovieEntity>.mapToLocalExercise(): List<Movie> =
    map { entity ->
        Movie(
            title = entity.title,
            overview = entity.overview,
            posterPath = entity.posterPath,
            releaseDate = entity.releaseDate,
            originalLanguage = entity.originalLanguage,
            voteAverage = entity.voteAverage,
            voteCount = entity.voteCount
        )
    }
