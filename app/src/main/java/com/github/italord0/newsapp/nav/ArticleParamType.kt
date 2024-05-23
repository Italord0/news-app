package com.github.italord0.newsapp.nav

import android.os.Bundle
import androidx.navigation.NavType
import com.github.italord0.newsapp.data.Article
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@Suppress("DEPRECATION")
class ArticleParamType : NavType<Article?>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Article? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Article? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return moshi.adapter(Article::class.java).fromJson(value)
    }

    override fun put(bundle: Bundle, key: String, value: Article?) {
        bundle.putParcelable(key, value)
    }
}