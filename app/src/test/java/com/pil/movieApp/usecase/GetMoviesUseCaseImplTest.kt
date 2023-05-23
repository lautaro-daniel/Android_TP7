package com.pil.movieApp.usecase

import com.pil.movieApp.domain.database.MovieDataBase
import com.pil.movieApp.domain.entity.Movie
import com.pil.movieApp.domain.service.MovieService
import com.pil.movieApp.domain.usecase.GetMovieUseCase
import com.pil.movieApp.domain.usecase.GetMoviesUserCaseImpl
import com.pil.movieApp.domain.util.CoroutineResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class GetMoviesUseCaseImplTest {

    @MockK
    private lateinit var movieService: MovieService

    @MockK
    private lateinit var dataBase: MovieDataBase

    @MockK
    private lateinit var movieList: List<Movie>

    private lateinit var getMovieListUseCase: GetMovieUseCase

    @Before
    fun init(){
        MockKAnnotations.init(this, relaxed = true)
        getMovieListUseCase = GetMoviesUserCaseImpl(movieService, dataBase)
    }

    @Test
    fun `When coroutine returns success`(){
        coEvery { movieService.getMovies() } returns CoroutineResult.Success(movieList)
        coEvery { dataBase.getAllMovies() } returns CoroutineResult.Success(movieList)

        val result = runBlocking { getMovieListUseCase() }

        coEvery { dataBase.insertMovies(movieList) }
        coEvery { dataBase.getAllMovies() }

        assertEquals(movieList, (result as CoroutineResult.Success).data)
    }

    @Test
    fun `When coroutine returns failure but database isn´t empty`(){
        coEvery { movieService.getMovies() } returns CoroutineResult.Failure(Exception())
        coEvery { dataBase.getAllMovies() } returns CoroutineResult.Success(movieList)

        val result = runBlocking { getMovieListUseCase() }

        coEvery { dataBase.getAllMovies() }

        assertEquals(movieList, (result as CoroutineResult.Success).data)
    }

    @Test
    fun `When coroutine returns failure and database is empty`(){
        coEvery { movieService.getMovies() } returns CoroutineResult.Failure(Exception(ERROR_MESSAGE))
        coEvery { dataBase.getAllMovies() } returns CoroutineResult.Failure(Exception(ERROR_MESSAGE))

        val result = runBlocking { getMovieListUseCase() }

        coEvery { dataBase.getAllMovies() }

        assertEquals(ERROR_MESSAGE, (result as CoroutineResult.Failure).exception.message)
    }

    companion object{
        private const val ERROR_MESSAGE = "ERROR"
    }
}