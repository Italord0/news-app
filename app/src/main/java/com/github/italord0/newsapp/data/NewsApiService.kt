package com.github.italord0.newsapp.data

import com.github.italord0.newsapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("everything")
    suspend fun getAllArticles(
        @Query(value = "apiKey") apiKey: String = BuildConfig.API_KEY
    ): NewsResponse

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query(value = "sources") sources: String = BuildConfig.NEWS_SOURCE,
        @Query(value = "apiKey") apiKey: String = BuildConfig.API_KEY
    ): NewsResponse
}

data class NewsResponse(val articles: List<Article>)
