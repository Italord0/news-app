package com.github.italord0.newsapp.usecase

import com.github.italord0.newsapp.data.NewsResponse
import com.github.italord0.newsapp.repository.ArticleRepository

class GetAllArticlesUseCaseImpl(
    private val repository: ArticleRepository
) : GetAllArticlesUseCase {
    override suspend fun invoke(): NewsResponse {
        return repository.getAllArticles()
    }
}