package com.morarafrank.dailybrief.data.remote

import com.morarafrank.dailybrief.BuildConfig
import com.morarafrank.dailybrief.domain.model.ArticleResponse
import com.morarafrank.dailybrief.domain.model.Source
import com.morarafrank.dailybrief.domain.model.SourcesResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    // Everything
    @GET("everything")
    suspend fun getAllNews(
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY,
        @Query("q") q: String? = null,
        @Query("searchIn") searchIn: String? = "title, description, content",
        @Query("sources") sources: Source?=null,
        @Query("sortBy") sortBy: String? = "publishedAt",
        @Query("domains") domains: String? = null,
        @Query("excludeDomains") excludeDomains: String? = null,
        @Query("from") from: String? = null,
        @Query("to") to: String? = null,
        @Query("language") language: String? = "en",
        @Query("pageSize") pageSize: Int?=100,
        @Query("page") page: Int?=1

    ): Response<ArticleResponse>

    // Search News from Everything endpoint.
    @GET("everything")
    suspend fun searchNews(
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY,
        @Query("q") q: String,
        @Query("searchIn") searchIn: String? = "title, description, content",
        @Query("sources") sources: Source?=null,
        @Query("sortBy") sortBy: String? = "relevancy",
        @Query("domains") domains: String? = null,
        @Query("excludeDomains") excludeDomains: String? = null,
        @Query("from") from: String? = null,
        @Query("to") to: String? = null,
        @Query("language") language: String? = "en",
        @Query("pageSize") pageSize: Int?=100,
        @Query("page") page: Int?=1

    ): Response<ArticleResponse>

//    Top-headlines
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY,
        @Query("country") country: String? = "us",
        @Query("category") category: String? = "general",
        @Query("sources") sources: String?=null,
        @Query("q") q: String? = null,
        @Query("pageSize") pageSize: Int?=100,
        @Query("page") page: Int?=1
): Response<ArticleResponse>


    //   Sources.
    @GET("top-headlines")
    suspend fun getNewsSources(
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY,
        @Query("category") category: String? = "general",
        @Query("language") language: String? = "en",
        @Query("country") country: String? = null,
        ): Response<SourcesResponse>

}