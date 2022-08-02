package com.mobile.myweatherapp.presentation.currentWeather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.mobile.myweatherapp.R
import com.mobile.myweatherapp.databinding.FragmentWeatherBinding
import com.mobile.myweatherapp.models.WeatherClass
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WeatherFragment : Fragment() {
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var binding: FragmentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getCurrTemp(1.1, 0.0)
        viewModel.weather.observe(viewLifecycleOwner) { weatherInfo -> updateWeatherView(weatherInfo) }
    }

    private fun updateWeatherView(weather: WeatherClass) {
        val currentWeather = weather.list[0]
        binding.apply {
            this.weather = weather
            currentWeather.let {
                humidityText.text =
                    resources.getString(R.string.humidity_percentage, it.main?.humidity)
                dateTimeText.text = weather.city?.name + " : " + it.dtTxt
                pressureText.text = resources.getString(R.string.atm_pressure, it.main?.pressure)
                windSpeedText.text = resources.getString(R.string.wind_speed, it.wind?.speed)
                temperatureText.text = resources.getString(R.string.temperature, it.main?.temp)
                realFeelText.text = resources.getString(R.string.temperature, it.main?.feelsLike)
                windDirectionText.text = it.wind?.speed.toString()
                visibilityText.text = resources.getString(R.string.visibility, it.visibility)
                weatherDescriptionText.text = it.weather?.get(0)?.description ?: ""
            }
        }

        binding.forecast.setOnClickListener(View.OnClickListener {
            val bundle = bundleOf("WEATHER" to weather)
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_weatherFragment_to_futureWeatherFragment, bundle)
        })
    }
}