package com.morarafrank.dailybrief.data.local

data class NewsItem(
    val id: Int,
    val title: String,
    val content: String,
    val publishedAt: String,
    val source: String,
    val url: String,
    val imageUrl: String,
)