package com.sneha.myweatherapp.repo

import com.sneha.myweatherapp.modals.WeatherClass
import com.sneha.myweatherapp.modals.WeatherInfoRequest
import com.sneha.myweatherapp.networking.remote.NetworkResponse
import java.lang.Error

interface DefaultRepository {
    suspend fun getWeatherInfo(weatherInfoRequest: WeatherInfoRequest): NetworkResponse<WeatherClass, Error>
}