package com.github.italord0.newsapp.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class HomeViewModel : ViewModel() {
    abstract val homeScreenState: StateFlow<HomeScreenState>
    abstract fun fetchArticles()
    abstract fun fetchAllArticles()
}