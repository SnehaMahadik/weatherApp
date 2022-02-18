package com.sneha.myweatherapp.repo

import com.sneha.myweatherapp.networking.WeatherInstance
import com.sneha.myweatherapp.constants.API_KEY


class WeatherRepository(private val instance: WeatherInstance) {

   suspend fun getCurrentWeather(query : Double,days : Double) = instance.getCurrentTemp(API_KEY, query,days)

}