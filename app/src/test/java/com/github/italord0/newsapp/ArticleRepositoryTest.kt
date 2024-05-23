package com.github.italord0.newsapp

import com.github.italord0.newsapp.data.NewsApiService
import com.github.italord0.newsapp.data.NewsResponse
import com.github.italord0.newsapp.repository.ArticleRepository
import com.github.italord0.newsapp.repository.ArticleRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ArticleRepositoryTest {

    private lateinit var apiService: NewsApiService
    private lateinit var repository: ArticleRepository

    @Before
    fun setUp() {
        apiService = mockk()
        repository = ArticleRepositoryImpl(apiService)
    }

    @Test
    fun `getAllArticles should call getAllArticles method of NewsApiService`() = runTest {
        // Given
        val expectedResponse = NewsResponse(listOf())
        coEvery { apiService.getAllArticles() } returns expectedResponse

        // When
        repository.getAllArticles()

        // Then
        coVerify { apiService.getAllArticles() }
    }

    @Test
    fun `getTopHeadlines should call getTopHeadlines method of NewsApiService`() = runTest {
        // Given
        val expectedResponse = NewsResponse(listOf())
        coEvery { apiService.getTopHeadlines() } returns expectedResponse

        // When
        repository.getTopHeadlines()

        // Then
        coVerify { apiService.getTopHeadlines() }
    }

    @Test
    fun `getAllArticles should return NewsResponse`() = runTest {
        // Given
        val expectedResponse = NewsResponse(listOf())
        coEvery { apiService.getAllArticles() } returns expectedResponse

        // When
        val actualResponse = repository.getAllArticles()

        // Then
        assertEquals(expectedResponse, actualResponse)
    }

    @Test
    fun `getTopHeadlines should return NewsResponse`() = runTest {
        // Given
        val expectedResponse = NewsResponse(listOf())
        coEvery { apiService.getTopHeadlines() } returns expectedResponse

        // When
        val actualResponse = repository.getTopHeadlines()

        // Then
        assertEquals(expectedResponse, actualResponse)
    }
}

