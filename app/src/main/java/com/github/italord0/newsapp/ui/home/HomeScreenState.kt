package com.github.italord0.newsapp.ui.home

import com.github.italord0.newsapp.data.Article
import com.github.italord0.newsapp.ui.ScreenState

data class HomeScreenState(
    val screenState: ScreenState = ScreenState.LOADING,
    val articles: List<Article> = listOf(),
    val error: Throwable? = null
)
