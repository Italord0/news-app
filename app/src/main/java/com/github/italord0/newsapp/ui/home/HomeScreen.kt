package com.github.italord0.newsapp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.italord0.newsapp.R
import com.github.italord0.newsapp.ui.ScreenState.ERROR
import com.github.italord0.newsapp.ui.ScreenState.LOADING
import com.github.italord0.newsapp.ui.ScreenState.SUCCESS
import com.github.italord0.newsapp.ui.components.ArticleCell
import com.github.italord0.newsapp.util.darkThemeColors
import com.github.italord0.newsapp.util.navigateParcelable
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = getViewModel(), navController: NavController) {
    Surface {
        val state = viewModel.homeScreenState.collectAsState().value

        LaunchedEffect(Unit) {
            viewModel.fetchArticles()
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .height(56.dp)
                        .padding(vertical = 16.dp),
                    tint = Color.White
                )
            }

            when (state.screenState) {
                LOADING -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = Color.White
                        )
                    }
                }

                SUCCESS -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(state.articles) { article ->
                            ArticleCell(article) {
                                navController.navigateParcelable(
                                    route = "details", args = bundleOf(
                                        "article" to article
                                    )
                                )
                            }
                        }
                    }
                }

                ERROR -> {
                    Text(
                        text = "Error fetching data : ${state.error}",
                        style = TextStyle(fontSize = 20.sp, color = Color.Red)
                    )
                }
            }


        }
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    MaterialTheme(colors = darkThemeColors) {
        val navController = rememberNavController()
        HomeScreen(HomeViewModelPreview(), navController)
    }
}