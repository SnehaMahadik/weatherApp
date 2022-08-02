package com.mobile.myweatherapp.domain

import com.mobile.myweatherapp.domain.base.UseCase
import com.mobile.myweatherapp.models.WeatherClass
import com.mobile.myweatherapp.models.WeatherInfoRequest
import com.mobile.myweatherapp.networking.remote.NetworkResponse
import java.lang.Error

interface WeatherInfoUseCase : UseCase<WeatherInfoRequest, NetworkResponse<WeatherClass, Error>> {
    override suspend fun executeUseCase(request: WeatherInfoRequest): NetworkResponse<WeatherClass, Error>
}