package com.github.italord0.newsapp

import com.github.italord0.newsapp.data.NewsResponse
import com.github.italord0.newsapp.ui.home.HomeViewModelImpl
import com.github.italord0.newsapp.usecase.GetAllArticlesUseCase
import com.github.italord0.newsapp.usecase.GetTopHeadlinesUseCase
import com.github.italord0.newsapp.util.mockArticle
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {


    private lateinit var getTopHeadlinesUseCase: GetTopHeadlinesUseCase
    private lateinit var getAllArticlesUseCase: GetAllArticlesUseCase
    private lateinit var viewModel: HomeViewModelImpl

    @get:Rule
    val retryRule = RetryRule(3)

    @Before
    fun setup() {
        getTopHeadlinesUseCase = mockk()
        getAllArticlesUseCase = mockk()
        viewModel = HomeViewModelImpl(getTopHeadlinesUseCase, getAllArticlesUseCase)
    }

    @Test
    fun `fetchArticles invokes GetTopHeadlinesUseCase`() = runTest {
        // Given
        val mockArticles = listOf(mockArticle, mockArticle)
        coEvery { getTopHeadlinesUseCase.invoke() } returns NewsResponse(mockArticles)

        // When
        viewModel.fetchArticles()

        // Then
        coVerify { getTopHeadlinesUseCase.invoke() }
    }

    @Test
    fun `fetchAllArticles invokes GetAllArticlesUseCase`() = runTest {
        // Given
        val mockArticles = listOf(mockArticle, mockArticle)
        coEvery { getAllArticlesUseCase.invoke() } returns NewsResponse(mockArticles)

        // When
        viewModel.fetchAllArticles()

        // Then
        coVerify { getAllArticlesUseCase.invoke() }
    }


}