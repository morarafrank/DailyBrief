package com.morarafrank.dailybrief.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morarafrank.dailybrief.data.repo.NewsRepository
import com.morarafrank.dailybrief.utils.NewsUiState
import com.morarafrank.dailybrief.utils.SourcesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor (
    private val newsRepository: NewsRepository
): ViewModel(){
    init {

    }

    private val _newsUiState = MutableStateFlow<NewsUiState>(NewsUiState.Idle)
    val newsUiState: StateFlow<NewsUiState> = _newsUiState.asStateFlow()

    private val _headlinesUiState = MutableStateFlow<NewsUiState>(NewsUiState.Idle)
    val headlinesUiState: StateFlow<NewsUiState> = _headlinesUiState.asStateFlow()

    // sources ui state
    private val _sourcesUiState = MutableStateFlow<SourcesUiState>(SourcesUiState.Idle)
    val sourcesUiState: StateFlow<SourcesUiState> = _sourcesUiState.asStateFlow()



    // Everything News
    fun getAllNews(
        query: String
    ) {
        viewModelScope.launch {
            _newsUiState.value = NewsUiState.Loading
            try {
                newsRepository.getAllNews(query)
                    .isSuccessful.let { response ->
                    if (response) {
                        val newsArticles = newsRepository.getAllNews(query).body()?.articles ?: emptyList()
                        _newsUiState.value = NewsUiState.Success(newsArticles)
                    } else {
                        throw Exception(
                            "Error fetching news: ${newsRepository.getAllNews(query).errorBody()?.string()}"
                        )
                    }
                }

            } catch (e: Exception) {
                Log.e(
                    "NewsViewModel",
                    "getEverythingNews: ${e.localizedMessage}",
                    )
                _newsUiState.value = NewsUiState
                    .Error(
                        e.localizedMessage ?: "An unexpected error occurred"
                    )
            }
        }
    }

    // Search News
    fun searchNewsArticles(query: String) = viewModelScope.launch {
        _newsUiState.value = NewsUiState.Loading
        try {
            newsRepository.searchNews(query).isSuccessful.let { response ->
                if (response) {
                    val newsArticles = newsRepository.searchNews(query).body()?.articles ?: emptyList()
                    _newsUiState.value = NewsUiState.Success(newsArticles)
                } else {
                    throw Exception("Error searching news: ${newsRepository.searchNews(query).errorBody()?.string()}")
                }
            }

        } catch (e: Exception) {
            Log.e("NewsViewModel", "searchNews: ${e.localizedMessage}", )
            _newsUiState.value = NewsUiState.Error(e.localizedMessage ?: "An unexpected error occurred")
        }
    }

    // Top Headlines
    fun getTopHeadlines() = viewModelScope.launch {
        _headlinesUiState.value = NewsUiState.Loading
        try {
            newsRepository.getTopHeadlines().isSuccessful.let { response ->
                if (response) {
                    val newsArticles = newsRepository.getTopHeadlines().body()?.articles ?: emptyList()
                    _headlinesUiState.value = NewsUiState.Success(newsArticles)
                } else {
                    throw Exception("Error fetching top headlines: ${newsRepository.getTopHeadlines().errorBody()?.string()}")
                }
            }

        } catch (e: Exception) {
            Log.e("NewsViewModel", "getTopHeadlines: ${e.localizedMessage}", )
            _headlinesUiState.value = NewsUiState.Error(e.localizedMessage ?: "An unexpected error occurred")
        }
    }

    // News Sources
    fun getNewsSources() = viewModelScope.launch {
        _sourcesUiState.value = SourcesUiState.Loading
        try {
            newsRepository.getNewsSources().isSuccessful.let { response ->
                if (response) {
                    val sources = newsRepository.getNewsSources().body()?.sources ?: emptyList()
                    _sourcesUiState.value = SourcesUiState.Success(sources)
                } else {
                    throw Exception("Error fetching news sources: ${newsRepository.getNewsSources().errorBody()?.string()}")
                }
            }

        } catch (e: Exception) {
            Log.e("NewsViewModel", "getNewsSources: ${e.localizedMessage}", )
            _sourcesUiState.value = SourcesUiState.Error(e.localizedMessage ?: "An unexpected error occurred")
        }
    }
}