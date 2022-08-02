package com.mobile.myweatherapp.domain;

import com.mobile.myweatherapp.models.WeatherClass
import com.mobile.myweatherapp.models.WeatherInfoRequest
import com.mobile.myweatherapp.networking.remote.NetworkResponse
import com.mobile.myweatherapp.domain.repo.DefaultRepository
import javax.inject.Inject
import java.lang.Error

class WeatherInfoUseCaseImpl @Inject constructor(var weatherRepository: DefaultRepository) :
    WeatherInfoUseCase {

    override suspend fun executeUseCase(request: WeatherInfoRequest): NetworkResponse<WeatherClass, Error> {
        return weatherRepository.getWeatherInfo(request)
    }


}
