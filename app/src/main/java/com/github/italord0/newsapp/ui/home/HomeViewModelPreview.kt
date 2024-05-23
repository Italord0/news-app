package com.github.italord0.newsapp.ui.home

import com.github.italord0.newsapp.ui.ScreenState
import com.github.italord0.newsapp.util.mockArticle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModelPreview : HomeViewModel() {

    private val _uiState: MutableStateFlow<HomeScreenState> = MutableStateFlow(
        HomeScreenState(
            ScreenState.SUCCESS,
            listOf(mockArticle, mockArticle, mockArticle, mockArticle)
        )
    )

    override val homeScreenState: StateFlow<HomeScreenState>
        get() = _uiState.asStateFlow()

    override fun fetchArticles() { }
    override fun fetchAllArticles() { }

}