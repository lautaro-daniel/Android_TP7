package com.pil.movieApp.service.model

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("count")
    var count: Int,
    @SerializedName("next")
    var next: String,
    @SerializedName("previous")
    var previous: String,
    @SerializedName("results")
    var movies: List<Movie>,
)
