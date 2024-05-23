package com.github.italord0.newsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.github.italord0.newsapp.R
import com.github.italord0.newsapp.data.Article
import com.github.italord0.newsapp.util.mockArticle

@Composable
fun ArticleCell(article: Article, onArticleClick: (Article) -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onArticleClick(article) },
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            AsyncImage(
                model = article.urlToImage.orEmpty(),
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = article.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = article.title,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = article.description.orEmpty(),
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
@Preview
fun ArticleCellPreview() {
    Surface {
        ArticleCell(article = mockArticle)
    }
}