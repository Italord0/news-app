package com.github.italord0.newsapp.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.github.italord0.newsapp.R
import com.github.italord0.newsapp.data.Article
import com.github.italord0.newsapp.util.darkThemeColors
import com.github.italord0.newsapp.util.mockArticle
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun DetailsScreen(
    navController: NavController, article: Article
) {

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Article Details") },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            })
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = article.urlToImage.orEmpty(),
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = article.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentScale = ContentScale.FillWidth
            )
            Text(
                modifier = Modifier.padding(16.dp),
                text = article.title,
                style = TextStyle(fontSize = 32.sp)
            )
            Text(modifier = Modifier.padding(16.dp), text = article.content.orEmpty())
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = {
                    val encodedUrl =
                        URLEncoder.encode(article.url, StandardCharsets.UTF_8.toString())
                    navController.navigate("webView/${encodedUrl}")
                }
            ) {
                Text(text = "Check on website")
            }
        }
    }

}

@Composable
@Preview
fun DetailsScreenPreview() {
    MaterialTheme(colors = darkThemeColors) {
        val navController = rememberNavController()
        DetailsScreen(navController, mockArticle)
    }
}