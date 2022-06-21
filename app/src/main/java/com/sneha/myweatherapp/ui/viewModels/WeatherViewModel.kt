package com.sneha.myweatherapp.ui.viewModels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sneha.myweatherapp.domain.WeatherInfoUseCase
import com.sneha.myweatherapp.modals.List
import com.sneha.myweatherapp.modals.WeatherClass
import com.sneha.myweatherapp.modals.WeatherInfoRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(var weatherInfoUseCase: WeatherInfoUseCase) : ViewModel() {

    val weather: MutableLiveData<WeatherClass> = MutableLiveData()

    fun getCurrTemp(lat: Double, lon: Double) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = weatherInfoUseCase.executeUseCase(WeatherInfoRequest(lat,lon))
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    weather.postValue(response.body())
                } else {
                    Log.d("Test", "Error fetching data")
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun isFutureDate(newDate: String): Boolean {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val date = sdf.parse(newDate)
        val millis = date?.time
        millis?.let {
            return it > Calendar.getInstance().timeInMillis
        }
        return false
    }

    @SuppressLint("SimpleDateFormat")
    fun getWeatherDataForDate(newDate: String): Map<Date?, List>? {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        var newDateFormatDate = ""
        val date = sdf.parse(newDate)
        val newDateFormat = SimpleDateFormat("dd MMM yyyy")
        date?.let {
            newDateFormatDate = newDateFormat.format(date)
        }
        val listWeatherInfoForDate = weather.value?.list?.filter { weatherList ->
            weatherList.dtTxt.let { newDateFormat.format(sdf.parse(it)) == newDateFormatDate }
        }
        listWeatherInfoForDate.isNullOrEmpty().let {  val map =
            listWeatherInfoForDate?.associateBy(keySelector = { weatherDate -> sdf.parse(weatherDate.dtTxt) })
            return map
        }


    }
}