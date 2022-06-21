package com.sneha.myweatherapp.domain;

import com.sneha.myweatherapp.modals.WeatherClass
import com.sneha.myweatherapp.modals.WeatherInfoRequest
import com.sneha.myweatherapp.repo.WeatherRepository
import retrofit2.Response
import javax.inject.Inject

class WeatherInfoUseCaseImpl @Inject constructor(var weatherRepository: WeatherRepository) : WeatherInfoUseCase{
 override suspend fun executeUseCase(request: WeatherInfoRequest): Response<WeatherClass> {
  return weatherRepository.getWeatherInfo(request)
 }


}
