package com.github.italord0.newsapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Parcelize
@JsonClass(generateAdapter = true)
data class Article(
    @Json(name = "source") val source: Source,
    @Json(name = "author") val author: String?,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String?,
    @Json(name = "url") val url: String,
    @Json(name = "urlToImage") val urlToImage: String?,
    @Json(name = "publishedAt") val publishedAt: String,
    @Json(name = "content") val content: String?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Source(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String
) : Parcelable

