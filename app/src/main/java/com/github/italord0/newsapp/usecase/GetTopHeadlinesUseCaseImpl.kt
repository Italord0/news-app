package com.github.italord0.newsapp.usecase

import com.github.italord0.newsapp.data.NewsResponse
import com.github.italord0.newsapp.repository.ArticleRepository

class GetTopHeadlinesUseCaseImpl(
    private val repository: ArticleRepository
) : GetTopHeadlinesUseCase {
    override suspend fun invoke(): NewsResponse {
        return repository.getTopHeadlines()
    }
}