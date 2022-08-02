package com.mobile.myweatherapp.ui.viewModels.data

import com.mobile.myweatherapp.models.City
import com.mobile.myweatherapp.models.WeatherClass
import com.mobile.myweatherapp.models.WeatherInfoRequest
import com.mobile.myweatherapp.networking.remote.NetworkResponse
import com.mobile.myweatherapp.domain.repo.DefaultRepository
import com.mobile.myweatherapp.ui.viewModels.getMockWeatherList
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