package com.morarafrank.skycast.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.morarafrank.weatherapp.domain.model.City
import com.morarafrank.weatherapp.domain.model.Clouds
import com.morarafrank.weatherapp.domain.model.ForecastItem
import com.morarafrank.weatherapp.domain.model.MainForecast
import com.morarafrank.weatherapp.domain.model.RemoteWeather
import com.morarafrank.weatherapp.domain.model.Wind
import kotlin.jvm.java

class Converters {
    private val gson = Gson()

//    @TypeConverter
//    fun fromWeatherList(value: List<RemoteWeather>): String {
//        return gson.toJson(value)
//    }
//
//    @TypeConverter
//    fun toWeatherList(value: String): List<RemoteWeather> {
//        val listType = object : TypeToken<List<RemoteWeather>>() {}.type
//        return gson.fromJson(value, listType)
//    }

    @TypeConverter
    fun fromMainForecast(mainForecast: MainForecast): String {
        return gson.toJson(mainForecast)
    }

    @TypeConverter
    fun toMainForecast(data: String): MainForecast {
        return gson.fromJson(data, MainForecast::class.java)
    }

    @TypeConverter
    fun fromRemoteWeatherList(weatherList: List<RemoteWeather>): String {
        return gson.toJson(weatherList)
    }

    @TypeConverter
    fun toRemoteWeatherList(data: String): List<RemoteWeather> {
        val listType = object : TypeToken<List<RemoteWeather>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun fromClouds(clouds: Clouds): String {
        return gson.toJson(clouds)
    }

    @TypeConverter
    fun toClouds(data: String): Clouds {
        return gson.fromJson(data, Clouds::class.java)
    }

    @TypeConverter
    fun fromWind(wind: Wind): String {
        return gson.toJson(wind)
    }

    @TypeConverter
    fun toWind(data: String): Wind {
        return gson.fromJson(data, Wind::class.java)
    }

    @TypeConverter
    fun fromForecastList(forecastList: List<ForecastItem>): String {
        return gson.toJson(forecastList)
    }

    @TypeConverter
    fun toForecastList(forecastString: String): List<ForecastItem> {
        val type = object : TypeToken<List<ForecastItem>>() {}.type
        return gson.fromJson(forecastString, type)
    }


    @TypeConverter
    fun fromCity(city: City): String {
        return gson.toJson(city)
    }

    @TypeConverter
    fun toCity(cityString: String): City {
        return gson.fromJson(cityString, City::class.java)
    }

}

