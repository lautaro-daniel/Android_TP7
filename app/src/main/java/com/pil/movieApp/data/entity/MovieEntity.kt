package com.pil.movieApp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie")
class MovieEntity(
    @PrimaryKey var title: String,
    var overview: String,
    var posterPath: String,
    var releaseDate: String,
    var originalLanguage: String,
    var voteAverage: Double,
    var voteCount: Int,
)
