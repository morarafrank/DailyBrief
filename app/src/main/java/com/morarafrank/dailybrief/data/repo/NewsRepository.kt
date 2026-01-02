package com.morarafrank.dailybrief.data.repo

import android.util.Log
import com.morarafrank.dailybrief.data.remote.NewsApiService
import com.morarafrank.dailybrief.domain.model.Article
import com.morarafrank.dailybrief.domain.model.ArticleResponse
import com.morarafrank.dailybrief.domain.model.SourcesResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(
    val newsApi: NewsApiService
){

    suspend fun getAllNews(): Response<ArticleResponse> {

        return try {
            newsApi.getAllNews()
        }catch (e: Exception){
            Log.e("NewsRepository", "getAllNews: ${e.localizedMessage}", )
            throw e
        }
    }

    suspend fun searchNews(query: String): Response<ArticleResponse> {
        return try {
            newsApi.searchNews(
                q = query,
            )
        }catch (e: Exception){
            Log.e("NewsRepository", "searchNews: ${e.localizedMessage}", )
            throw e
        }
    }

    suspend fun getTopHeadlines(): Response<ArticleResponse> {
        return try {
            newsApi.getTopHeadlines()
        }catch (e: Exception){
            Log.e("NewsRepository", "getTopHeadlines: ${e.localizedMessage}", )
            throw e
        }
    }

    suspend fun getNewsSources(): Response<SourcesResponse>{
        return try {
            newsApi.getNewsSources()
        }catch (e: Exception){
            Log.e("NewsRepository", "getNewsSources: ${e.localizedMessage}", )
            throw e
        }
    }


}