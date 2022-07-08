package com.sneha.myweatherapp.ui.viewModels.data

import com.sneha.myweatherapp.modals.City
import com.sneha.myweatherapp.modals.WeatherClass
import com.sneha.myweatherapp.modals.WeatherInfoRequest
import com.sneha.myweatherapp.networking.remote.NetworkResponse
import com.sneha.myweatherapp.repo.DefaultRepository
import com.sneha.myweatherapp.ui.viewModels.getMockWeatherList
import java.lang.Error

class FakeWeatherRepository : DefaultRepository {

    private var isSuccess: Boolean = true

    fun setApiResponse(value: Boolean) {
        isSuccess = value
    }

    override suspend fun getWeatherInfo(weatherInfoRequest: WeatherInfoRequest): NetworkResponse<WeatherClass, Error> {
        return if (isSuccess) {
            NetworkResponse.Success(WeatherClass(list = getMockWeatherList(), city = City()))
        } else {
            NetworkResponse.ApiError(code = 503, body = null)
        }

    }
}