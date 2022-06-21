package com.sneha.myweatherapp.repo

import com.sneha.myweatherapp.modals.WeatherClass
import com.sneha.myweatherapp.modals.WeatherInfoRequest
import retrofit2.Response

interface DefaultRepository {
    suspend fun getWeatherInfo (weatherInfoRequest: WeatherInfoRequest): Response<WeatherClass>
}