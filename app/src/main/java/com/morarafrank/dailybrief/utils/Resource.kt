package com.morarafrank.dailybrief.utils

import com.morarafrank.dailybrief.domain.model.Article
import com.morarafrank.dailybrief.domain.model.Source

sealed class Resource<out T : Any> {
    object Idle: Resource<Nothing>()
    object Loading : Resource<Nothing>()
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val throwable: Throwable) : Resource<Nothing>()
}

sealed class NewsUiState {
    object Idle : NewsUiState()
    object Loading : NewsUiState()
    data class Success(val data: List<Article>) : NewsUiState()
    data class Error(val message: String) : NewsUiState()
}

sealed class SourcesUiState {
    object Idle : SourcesUiState()
    object Loading : SourcesUiState()
    data class Success(val data: List<Source>) : SourcesUiState()
    data class Error(val message: String) : SourcesUiState()
}