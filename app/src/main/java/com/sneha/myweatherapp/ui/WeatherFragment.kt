package com.sneha.myweatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.freenow.myweatherapp.R
import com.freenow.myweatherapp.databinding.FragmentWeatherBinding
import com.sneha.myweatherapp.modals.WeatherClass
import com.sneha.myweatherapp.ui.viewModels.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WeatherFragment : Fragment() {
    private val viewModel:WeatherViewModel by viewModels()
    private lateinit var binding: FragmentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setup()
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setup() {
        viewModel.getCurrTemp(51.5072, 0.1276)
        viewModel.weather.observe(viewLifecycleOwner) { weatherInfo -> updateWeatherView(weatherInfo) }
    }

    private fun updateWeatherView(weather: WeatherClass) {
        binding.weather = weather
        val currentWeather = weather.list[0]
        binding.humidityText.text =
            resources.getString(R.string.humidity_percentage, currentWeather.main?.humidity)
        binding.dateTimeText.text = weather.city?.name + " : " + currentWeather.dtTxt
        binding.pressureText.text =
            resources.getString(R.string.atm_pressure, currentWeather.main?.pressure)
        binding.windSpeedText.text =
            resources.getString(R.string.wind_speed, currentWeather.wind?.speed)
        binding.temperatureText.text =
            resources.getString(R.string.temperature, currentWeather.main?.temp)
        binding.realFeelText.text =
            resources.getString(R.string.temperature, currentWeather.main?.feelsLike)
        binding.windDirectionText.text = currentWeather.wind?.speed.toString()
        binding.visibilityText.text =
            resources.getString(R.string.visibility, currentWeather.visibility)
        binding.weatherDescriptionText.text = currentWeather.weather?.get(0)?.description ?: ""

        binding.forecast.setOnClickListener(View.OnClickListener {
            val bundle = bundleOf("WEATHER" to weather)
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_weatherFragment_to_futureWeatherFragment, bundle)
        })

    }
}