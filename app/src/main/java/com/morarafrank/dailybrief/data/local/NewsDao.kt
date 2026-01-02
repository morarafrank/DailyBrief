package com.morarafrank.dailybrief.data.local

interface NewsDao {

    suspend fun insertNewsItem(newsItem: NewsItem)

    suspend fun getAllNewsItems(): List<NewsItem>

    suspend fun deleteNewsItemById(id: Int)
}