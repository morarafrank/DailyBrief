package com.morarafrank.dailybrief.domain.model

data class NewsDTO(
    val id: String,
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val publishedAt: String,
    val source: String
)