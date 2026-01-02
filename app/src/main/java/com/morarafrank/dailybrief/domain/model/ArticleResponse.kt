package com.morarafrank.dailybrief.domain.model

data class ArticleResponse(
    val status: String? = null,
    val totalResults: Int? = null,
    val articles: List<Article>?,
)