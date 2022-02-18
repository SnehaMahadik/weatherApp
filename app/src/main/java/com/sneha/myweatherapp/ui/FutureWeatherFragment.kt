package com.sneha.myweatherapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.freenow.myweatherapp.databinding.FragmentFutureWeatherBinding
import com.sneha.myweatherapp.modals.List
import com.sneha.myweatherapp.viewModels.WeatherViewModel
import com.sneha.myweatherapp.modals.WeatherClass
import java.util.*


class FutureWeatherFragment : Fragment() {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var binding: FragmentFutureWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFutureWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(WeatherViewModel::class.java)
        val weather = arguments?.get("WEATHER") as WeatherClass
        setFutureWeatherData(weather)
    }

    private fun setFutureWeatherData(weathers: WeatherClass) {
        for (weather in weathers.list) {
            weather.dtTxt?.let { newDate ->
                if (viewModel.isFutureDate(newDate)) {

                    val priceText = TextView(requireActivity())
                    priceText.text = newDate
                    val serviceDescParams = ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_PARENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT
                    )
                    priceText.layoutParams = serviceDescParams
                    binding.scrollView.addView(priceText)

                    Log.d("....", "" + weather.dtTxt)
                    viewModel.getWeatherDataForDate(newDate)?.forEach { (key, value) ->
                        println("$key = ${value.weather[0].description}")
                        addView(value)
                    }
                }
            }

        }
    }

    private fun addView(weatherList: List) {
        for (weatherInfo in weatherList.weather) {
            val priceText = TextView(requireActivity())
            priceText.text = weatherInfo.description
            binding.scrollView.addView(priceText)
        }
        val priceText = TextView(requireActivity())
        priceText.text = "\n"
        binding.scrollView.addView(priceText)
    }
}