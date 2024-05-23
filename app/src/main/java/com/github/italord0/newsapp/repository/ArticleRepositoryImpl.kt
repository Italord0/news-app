package com.github.italord0.newsapp.repository

import com.github.italord0.newsapp.data.NewsApiService
import com.github.italord0.newsapp.data.NewsResponse

class ArticleRepositoryImpl(
    private val apiService: NewsApiService
) : ArticleRepository {
    override suspend fun getAllArticles(): NewsResponse {
        return apiService.getAllArticles()
    }

    override suspend fun getTopHeadlines(): NewsResponse {
        return apiService.getTopHeadlines()
    }
}