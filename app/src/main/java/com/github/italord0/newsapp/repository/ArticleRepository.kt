package com.github.italord0.newsapp.repository

import com.github.italord0.newsapp.data.NewsResponse

interface ArticleRepository {
    suspend fun getAllArticles(): NewsResponse
    suspend fun getTopHeadlines(): NewsResponse
}