package com.sneha.myweatherapp.domain

import com.sneha.myweatherapp.domain.base.UseCase
import com.sneha.myweatherapp.modals.WeatherClass
import com.sneha.myweatherapp.modals.WeatherInfoRequest
import retrofit2.Response

interface WeatherInfoUseCase : UseCase<WeatherInfoRequest,Response<WeatherClass>>{
    override suspend fun executeUseCase(request: WeatherInfoRequest): Response<WeatherClass>
}