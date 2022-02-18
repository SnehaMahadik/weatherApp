package com.sneha.myweatherapp.repo

import com.sneha.myweatherapp.networking.WeatherInstance


class WeatherRepository(private val instance: WeatherInstance) {

   suspend fun getCurrentWeather(key : String ,query : Double,days : Double) = instance.getCurrentTemp(key,query,days)

}