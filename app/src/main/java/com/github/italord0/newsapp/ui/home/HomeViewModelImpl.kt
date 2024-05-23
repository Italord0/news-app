package com.github.italord0.newsapp.ui.home

import androidx.lifecycle.viewModelScope
import com.github.italord0.newsapp.ui.ScreenState
import com.github.italord0.newsapp.usecase.GetAllArticlesUseCase
import com.github.italord0.newsapp.usecase.GetTopHeadlinesUseCase
import com.github.italord0.newsapp.util.mockArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModelImpl(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
    private val getAllArticlesUseCase: GetAllArticlesUseCase
) : HomeViewModel() {

    private val _uiState: MutableStateFlow<HomeScreenState> = MutableStateFlow(
        HomeScreenState(
            screenState = ScreenState.LOADING,
            articles = listOf()
        )
    )

    override val homeScreenState: StateFlow<HomeScreenState>
        get() = _uiState.asStateFlow()

    override fun fetchArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val newsResponse = getTopHeadlinesUseCase.invoke()
                _uiState.value = HomeScreenState(
                    screenState = ScreenState.SUCCESS,
                    articles = newsResponse.articles
                )
            }.onFailure { e ->
                _uiState.value =
                    HomeScreenState(screenState = ScreenState.ERROR, articles = listOf(), error = e)
            }
        }
    }

    override fun fetchAllArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val newsResponse = getAllArticlesUseCase.invoke()
                _uiState.value = HomeScreenState(
                    screenState = ScreenState.SUCCESS,
                    articles = newsResponse.articles
                )
            }.onFailure { e ->
                _uiState.value =
                    HomeScreenState(screenState = ScreenState.ERROR, articles = listOf(), error = e)
            }
        }
    }


}