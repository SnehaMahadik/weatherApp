package com.sneha.myweatherapp.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sneha.myweatherapp.domain.WeatherInfoUseCase

class WeatherViewModelFactory(var weatherInfoUseCase: WeatherInfoUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            WeatherViewModel(weatherInfoUseCase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}