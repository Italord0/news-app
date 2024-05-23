package com.github.italord0.newsapp

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.italord0.newsapp.data.NewsResponse
import com.github.italord0.newsapp.ui.ScreenState
import com.github.italord0.newsapp.ui.home.HomeScreenState
import com.github.italord0.newsapp.ui.home.HomeViewModelImpl
import com.github.italord0.newsapp.usecase.GetAllArticlesUseCase
import com.github.italord0.newsapp.usecase.GetTopHeadlinesUseCase
import com.github.italord0.newsapp.util.mockArticle
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.createTestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {


    private lateinit var getTopHeadlinesUseCase: GetTopHeadlinesUseCase
    private lateinit var getAllArticlesUseCase: GetAllArticlesUseCase
    private lateinit var viewModel: HomeViewModelImpl

    @Before
    fun setup() {
        getTopHeadlinesUseCase = mockk()
        getAllArticlesUseCase = mockk()
        viewModel = HomeViewModelImpl(getTopHeadlinesUseCase, getAllArticlesUseCase)
    }

    @Test
    fun `fetchArticles invokes GetTopHeadlinesUseCase and updates state correctly`() = runBlockingTest {
        // Given
        val mockArticles = listOf(mockArticle, mockArticle)
        val successState = HomeScreenState(ScreenState.SUCCESS, articles = mockArticles)
        coEvery { getTopHeadlinesUseCase.invoke() } returns NewsResponse(mockArticles)

        // When
        viewModel.fetchArticles()

        // Then
        coVerify { getTopHeadlinesUseCase.invoke() }
        assertEquals(successState, viewModel.homeScreenState.value)
    }

    @Test
    fun `fetchArticles handles failure and updates state correctly`() = runBlockingTest {
        // Given
        val error = RuntimeException("Something went wrong")
        val failureState = HomeScreenState(ScreenState.ERROR, articles = listOf(), error = error)
        coEvery { getTopHeadlinesUseCase.invoke() } throws error

        // When
        viewModel.fetchArticles()

        // Then
        coVerify { getTopHeadlinesUseCase.invoke() }
        assertEquals(failureState, viewModel.homeScreenState.value)
    }

    @Test
    fun `fetchAllArticles invokes GetAllArticlesUseCase and updates state correctly`() = runBlockingTest {
        // Given
        val mockArticles = listOf(mockArticle, mockArticle)
        val successState = HomeScreenState(ScreenState.SUCCESS, articles = mockArticles)
        coEvery { getAllArticlesUseCase.invoke() } returns NewsResponse(mockArticles)

        // When
        viewModel.fetchAllArticles()

        // Then
        coVerify { getAllArticlesUseCase.invoke() }
        assertEquals(successState, viewModel.homeScreenState.value)
    }

    @Test
    fun `fetchAllArticles handles failure and updates state correctly`() = runBlockingTest {
        // Given
        val error = RuntimeException("Something went wrong")
        val failureState = HomeScreenState(ScreenState.ERROR, articles = listOf(), error = error)
        coEvery { getAllArticlesUseCase.invoke() } throws error

        // When
        viewModel.fetchAllArticles()

        // Then
        coVerify { getAllArticlesUseCase.invoke() }
        assertEquals(failureState, viewModel.homeScreenState.value)
    }


}