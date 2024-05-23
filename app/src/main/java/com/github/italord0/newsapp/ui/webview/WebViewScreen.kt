package com.github.italord0.newsapp.ui.webview

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(
    url: String,
    navController: NavController,
    webViewClient: WebViewClient = WebViewClient()
) {

    val secureUrl = if (url.startsWith("http://")) {
        url.replace("http://", "https://")
    } else {
        url
    }

    MaterialTheme {
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "Article from source") },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                })
        }) {
            AndroidView(
                modifier = Modifier.padding(it),
                factory = { context ->
                    WebView(context).apply {
                        this.webViewClient = webViewClient
                        settings.javaScriptEnabled = true
                    }
                },
                update = { webView ->
                    webView.loadUrl(secureUrl)
                }
            )
        }
    }

}