package com.sneha.myweatherapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sneha.myweatherapp.repo.WeatherRepository

class WeatherViewModelFactory(private val weatherRepository: WeatherRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            WeatherViewModel(this.weatherRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}