package com.sneha.myweatherapp.repo

import com.sneha.myweatherapp.networking.WeatherInstance
import com.sneha.myweatherapp.constants.API_KEY
import com.sneha.myweatherapp.modals.WeatherClass
import com.sneha.myweatherapp.modals.WeatherInfoRequest
import com.sneha.myweatherapp.networking.remote.NetworkResponse
import java.lang.Error
import javax.inject.Inject


class WeatherRepository @Inject constructor(private val instance: WeatherInstance) :
    DefaultRepository {

    override suspend fun getWeatherInfo(weatherInfoRequest: WeatherInfoRequest): NetworkResponse<WeatherClass, Error> {
        return instance.getCurrentTemp(API_KEY, weatherInfoRequest.lat, weatherInfoRequest.long)
    }

}