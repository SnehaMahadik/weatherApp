package com.mobile.myweatherapp.presentation.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mobile.myweatherapp.R
import com.mobile.myweatherapp.databinding.FragmentFutureWeatherBinding
import com.mobile.myweatherapp.models.List
import com.mobile.myweatherapp.models.WeatherClass
import com.mobile.myweatherapp.presentation.currentWeather.WeatherViewModel

class FutureWeatherFragment : Fragment() {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var binding: FragmentFutureWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFutureWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(requireActivity()).get(WeatherViewModel::class.java)
        val weather = arguments?.get("WEATHER") as WeatherClass
        setFutureWeatherData(weather)
    }

    private fun setFutureWeatherData(weathers: WeatherClass) {
        for (weather in weathers.list) {
            weather.dtTxt.let { newDate ->
                if (viewModel.isFutureDate(newDate)) {
                    val dateText = TextView(requireActivity())
                    dateText.text = newDate
                    dateText.setTextColor(resources.getColor(R.color.purple_700))
                    binding.scrollView.addView(dateText)
                    viewModel.getWeatherDataForDate(newDate)?.forEach { (key, value) ->
                        println("$key = ${value.weather}")
                        addView(value)
                    }
                }
            }
        }
    }

    private fun addView(weatherList: List) {
        for (weatherInfo in weatherList.weather!!) {
            val weatherInfoText = TextView(requireActivity())
            weatherInfoText.text = resources.getString(
                R.string.weatherDescription,
                weatherInfo.description,
                weatherList.wind
            )
            binding.scrollView.addView(weatherInfoText)
        }
        val newLine = TextView(requireActivity())
        newLine.text = "\n"
        binding.scrollView.addView(newLine)
    }
}