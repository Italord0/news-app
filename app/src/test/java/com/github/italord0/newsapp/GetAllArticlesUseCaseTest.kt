package com.github.italord0.newsapp

import com.github.italord0.newsapp.data.NewsApiService
import com.github.italord0.newsapp.data.NewsResponse
import com.github.italord0.newsapp.repository.ArticleRepository
import com.github.italord0.newsapp.repository.ArticleRepositoryImpl
import com.github.italord0.newsapp.usecase.GetAllArticlesUseCase
import com.github.italord0.newsapp.usecase.GetAllArticlesUseCaseImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetAllArticlesUseCaseTest {

    private lateinit var repository: ArticleRepository
    private lateinit var apiService: NewsApiService
    private lateinit var getAllArticlesUseCase: GetAllArticlesUseCase

    @Before
    fun setUp() {
        apiService = mockk()
        repository = ArticleRepositoryImpl(apiService)
        getAllArticlesUseCase = GetAllArticlesUseCaseImpl(repository)
    }

    @Test
    fun `getAllArticlesUseCase should call getAllArticles method of ArticleRepository and service`() =
        runTest {
            // Given
            val expectedResponse = NewsResponse(listOf())
            coEvery { repository.getAllArticles() } returns expectedResponse

            // When
            getAllArticlesUseCase.invoke()

            // Then
            coVerify { repository.getAllArticles() }
            coVerify { apiService.getAllArticles() }
        }
}