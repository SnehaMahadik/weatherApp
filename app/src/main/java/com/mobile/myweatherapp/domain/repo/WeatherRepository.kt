package com.mobile.myweatherapp.domain.repo

import com.mobile.myweatherapp.networking.WeatherInstance
import com.mobile.myweatherapp.constants.API_KEY
import com.mobile.myweatherapp.models.WeatherClass
import com.mobile.myweatherapp.models.WeatherInfoRequest
import com.mobile.myweatherapp.networking.remote.NetworkResponse
import java.lang.Error
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val instance: WeatherInstance) :
    DefaultRepository {

    override suspend fun getWeatherInfo(weatherInfoRequest: WeatherInfoRequest): NetworkResponse<WeatherClass, Error> {
        return instance.getCurrentTemp(API_KEY, weatherInfoRequest.lat, weatherInfoRequest.long)
    }

}