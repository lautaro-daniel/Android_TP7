package com.pil.movieApp.mvvm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pil.movieApp.mvvm.contract.MainContract
import com.pil.movieApp.service.model.Movie
import com.pil.movieApp.util.CoroutineResult
import io.mockk.*
import org.junit.Rule
import org.junit.rules.TestRule
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import java.lang.Exception

class MainViewModelTest{

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @MockK
    private lateinit var model: MainContract.Model

    @MockK
    private lateinit var movieList: List<Movie>

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = MainViewModel(model)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `If call service is successful should get film listings`(){
        coEvery { model.getMovies() } returns CoroutineResult.Success(movieList)

        runBlocking { viewModel.callService().join() }

        assertEquals(movieList, viewModel.getValue().value?.movies)
        assertEquals(MainViewModel.MainStatus.SHOW_INFO, viewModel.getValue().value?.status)
    }

    @Test
    fun `If call service is failure should get an error message`(){
        coEvery { model.getMovies() } returns CoroutineResult.Failure(Exception())

        runBlocking { viewModel.callService().join() }

        assertEquals(MainViewModel.MainStatus.ERROR, viewModel.getValue().value?.status)
    }



}