package com.github.italord0.newsapp

import com.github.italord0.newsapp.data.NewsApiService
import com.github.italord0.newsapp.data.NewsResponse
import com.github.italord0.newsapp.repository.ArticleRepository
import com.github.italord0.newsapp.repository.ArticleRepositoryImpl
import com.github.italord0.newsapp.usecase.GetAllArticlesUseCase
import com.github.italord0.newsapp.usecase.GetAllArticlesUseCaseImpl
import com.github.italord0.newsapp.usecase.GetTopHeadlinesUseCase
import com.github.italord0.newsapp.usecase.GetTopHeadlinesUseCaseImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetTopHeadlinesUseCaseTest {

    private lateinit var repository: ArticleRepository
    private lateinit var apiService: NewsApiService
    private lateinit var getTopHeadlinesUseCase: GetTopHeadlinesUseCase

    @Before
    fun setUp() {
        apiService = mockk()
        repository = ArticleRepositoryImpl(apiService)
        getTopHeadlinesUseCase = GetTopHeadlinesUseCaseImpl(repository)
    }

    @Test
    fun `getTopHeadlinesUseCase should call getTopHeadlines method of ArticleRepository and service`() =
        runTest {
            // Given
            val expectedResponse = NewsResponse(listOf())
            coEvery { repository.getTopHeadlines() } returns expectedResponse

            // When
            getTopHeadlinesUseCase.invoke()

            // Then
            coVerify { repository.getTopHeadlines() }
            coVerify { apiService.getTopHeadlines() }
        }
}