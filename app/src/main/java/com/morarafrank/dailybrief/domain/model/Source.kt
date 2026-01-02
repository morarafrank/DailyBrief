package com.morarafrank.dailybrief.domain.model

import androidx.room.TypeConverter

data class Source(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val url: String? = null,
    val category: String? = null,
    val language: String? = null,
    val country: String? = null
)

data class SourcesResponse(
    val status: String? = null,
    val sources: List<Source>?
)

class SourceTypeConverters {
    @TypeConverter
    fun toString(source: Source?): String?{
        return source?.name
    }
}