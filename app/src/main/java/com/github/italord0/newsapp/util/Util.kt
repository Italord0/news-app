package com.github.italord0.newsapp.util

import android.os.Bundle
import androidx.compose.material.darkColors
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.github.italord0.newsapp.data.Article
import com.github.italord0.newsapp.data.Source
import android.content.Context
import java.util.Properties

fun getProperty(context: Context, key: String): String {
    val properties = Properties()
    context.assets.open("local.properties").use { inputStream ->
        properties.load(inputStream)
    }
    return properties.getProperty(key)
}

val mockArticle = Article(
    Source(
        id = "bbc-news",
        name = "BBC News"
    ),
    "BBC News",
    "Kate Tatler portrait prompts strong reaction online",
    "A painting of the Princess of Wales prompts a sceptical response on social media.",
    url = "\"https://www.bbc.co.uk/news/uk-69049830",
    urlToImage = "https://ichef.bbci.co.uk/news/1024/branded_news/0CB5/production/_133335230_hi095020328.jpg",
    publishedAt = "2024-05-22T20:37:21.9694009Z",
    content = "But the painting also had supporters. \\\"I actually don't mind it because I don't think the painting was meant to look like the Princess of Wales in a realistic way, but more like an image we recognise… [+1241 chars]"
)

fun NavController.navigateParcelable(
    route: String,
    args: Bundle,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    val nodeId = graph.findNode(route = route)?.id
    if (nodeId != null) {
        navigate(nodeId, args, navOptions, navigatorExtras)
    }
}

val darkThemeColors = darkColors(
    primary = Color.Black,
    onPrimary = Color.White
)