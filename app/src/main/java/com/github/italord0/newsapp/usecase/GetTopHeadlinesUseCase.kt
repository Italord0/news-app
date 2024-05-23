package com.github.italord0.newsapp.usecase

import com.github.italord0.newsapp.data.NewsResponse

interface GetTopHeadlinesUseCase {
    suspend fun invoke(): NewsResponse
}