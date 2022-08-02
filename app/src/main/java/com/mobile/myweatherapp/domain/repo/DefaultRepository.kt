package com.mobile.myweatherapp.domain.repo

import com.mobile.myweatherapp.models.WeatherClass
import com.mobile.myweatherapp.models.WeatherInfoRequest
import com.mobile.myweatherapp.networking.remote.NetworkResponse
import java.lang.Error

interface DefaultRepository {
    suspend fun getWeatherInfo(weatherInfoRequest: WeatherInfoRequest): NetworkResponse<WeatherClass, Error>
}