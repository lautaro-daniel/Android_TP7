package com.pil.movieApp.mapper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pil.movieApp.data.entity.MovieEntity
import com.pil.movieApp.data.service.utils.toMovie
import com.pil.movieApp.data.service.utils.toMovieDB
import com.pil.movieApp.data.service.utils.toMovieList
import com.pil.movieApp.domain.entity.Movie
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TransformExtensionTest {

    private lateinit var movie: Movie
    private lateinit var movieEntity: MovieEntity
    private lateinit var movieEntityList: List<MovieEntity>

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        movie = Movie(TITLE, OVERVIEW, POSTER_PATH, RELEASE_DATE, ORIGINAL_LANGUAGE, VOTE_AVERAGE, VOTE_COUNT)
        movieEntity = MovieEntity(TITLE, OVERVIEW, POSTER_PATH, RELEASE_DATE, ORIGINAL_LANGUAGE, VOTE_AVERAGE, VOTE_COUNT)
        movieEntityList = listOf(MovieEntity(TITLE, OVERVIEW, POSTER_PATH, RELEASE_DATE, ORIGINAL_LANGUAGE, VOTE_AVERAGE, VOTE_COUNT))
    }

    @Test
    fun `Transform movie to MovieDB`(){
        movie.toMovieDB()

        assertEquals(TITLE, movie.title)
        assertEquals(OVERVIEW, movie.overview)
        assertEquals(POSTER_PATH, movie.posterPath)
        assertEquals(RELEASE_DATE, movie.releaseDate)
        assertEquals(ORIGINAL_LANGUAGE, movie.originalLanguage)
        assertEquals(VOTE_COUNT, movie.voteCount)
    }

    @Test
    fun `Transform list of films entities to MovieDB list`(){
        val dbToMovieList = movieEntityList.toMovieList()

        assertEquals(TITLE, dbToMovieList[0].title)
        assertEquals(OVERVIEW, dbToMovieList[0].overview)
        assertEquals(POSTER_PATH, dbToMovieList[0].posterPath)
        assertEquals(RELEASE_DATE, dbToMovieList[0].releaseDate)
        assertEquals(ORIGINAL_LANGUAGE, dbToMovieList[0].originalLanguage)
        assertEquals(VOTE_COUNT, dbToMovieList[0].voteCount)
    }

    @Test
    fun `Transform MovieDB to Movie entity`(){
        val dbToMovieEntity = movieEntity.toMovie()

        assertEquals(TITLE, dbToMovieEntity.title)
        assertEquals(OVERVIEW, dbToMovieEntity.overview)
        assertEquals(POSTER_PATH, dbToMovieEntity.posterPath)
        assertEquals(RELEASE_DATE, dbToMovieEntity.releaseDate)
        assertEquals(ORIGINAL_LANGUAGE, dbToMovieEntity.originalLanguage)
        assertEquals(VOTE_COUNT, dbToMovieEntity.voteCount)
    }

    companion object {
        const val TITLE = "Movie Title"
        const val OVERVIEW = "Movie Overview"
        const val POSTER_PATH = "/movieImage.jpg"
        const val RELEASE_DATE = "Movie Release Date"
        const val ORIGINAL_LANGUAGE = "Movie Language"
        const val VOTE_AVERAGE = 37.7
        const val VOTE_COUNT = 400
    }
}