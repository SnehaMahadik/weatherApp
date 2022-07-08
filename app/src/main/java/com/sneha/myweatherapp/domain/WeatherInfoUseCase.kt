package com.sneha.myweatherapp.domain

import com.sneha.myweatherapp.domain.base.UseCase
import com.sneha.myweatherapp.modals.WeatherClass
import com.sneha.myweatherapp.modals.WeatherInfoRequest
import com.sneha.myweatherapp.networking.remote.NetworkResponse
import java.lang.Error

interface WeatherInfoUseCase : UseCase<WeatherInfoRequest, NetworkResponse<WeatherClass, Error>> {
    override suspend fun executeUseCase(request: WeatherInfoRequest): NetworkResponse<WeatherClass, Error>
}