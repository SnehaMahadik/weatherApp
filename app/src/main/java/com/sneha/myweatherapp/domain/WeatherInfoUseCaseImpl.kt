package com.sneha.myweatherapp.domain;

import com.sneha.myweatherapp.modals.WeatherClass
import com.sneha.myweatherapp.modals.WeatherInfoRequest
import com.sneha.myweatherapp.networking.remote.NetworkResponse
import com.sneha.myweatherapp.repo.DefaultRepository
import javax.inject.Inject
import java.lang.Error


class WeatherInfoUseCaseImpl @Inject constructor(var weatherRepository: DefaultRepository) :
    WeatherInfoUseCase {

    override suspend fun executeUseCase(request: WeatherInfoRequest): NetworkResponse<WeatherClass, Error> {
        return weatherRepository.getWeatherInfo(request)
    }


}
