package com.github.italord0.newsapp

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.italord0.newsapp.data.Article
import com.github.italord0.newsapp.di.networkModule
import com.github.italord0.newsapp.di.repositoryModule
import com.github.italord0.newsapp.di.useCaseModule
import com.github.italord0.newsapp.di.viewModelModule
import com.github.italord0.newsapp.nav.ArticleParamType
import com.github.italord0.newsapp.ui.details.DetailsScreen
import com.github.italord0.newsapp.ui.home.HomeScreen
import com.github.italord0.newsapp.ui.webview.WebViewScreen
import com.github.italord0.newsapp.util.darkThemeColors
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidContext(this@MainActivity)
            modules(
                viewModelModule,
                repositoryModule,
                useCaseModule,
                networkModule
            )
        }
        setContent {
            MainScreen()
        }
    }
}

@Composable
@Suppress("DEPRECATION")
fun MainScreen() {
    val navController = rememberNavController()
    MaterialTheme(colors = darkThemeColors) {
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") {
                HomeScreen(navController = navController)
            }
            composable("details", arguments = listOf(navArgument("article") {
                type = ArticleParamType()
            })) {
                val article = it.arguments?.getParcelable<Article>("article")
                article?.let {
                    DetailsScreen(navController = navController, article)
                }
            }
            composable(
                "webView/{url}",
                arguments = listOf(navArgument("url") { type = NavType.StringType })
            ) { backStackEntry ->
                WebViewScreen(
                    url = backStackEntry.arguments?.getString("url").orEmpty(),
                    navController = navController
                )
            }
        }
    }
}