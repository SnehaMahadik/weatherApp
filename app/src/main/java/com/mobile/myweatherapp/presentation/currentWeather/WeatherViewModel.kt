package com.mobile.myweatherapp.presentation.currentWeather

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.myweatherapp.domain.WeatherInfoUseCase
import com.mobile.myweatherapp.models.List
import com.mobile.myweatherapp.models.WeatherClass
import com.mobile.myweatherapp.models.WeatherInfoRequest
import com.mobile.myweatherapp.networking.remote.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(var weatherInfoUseCase: WeatherInfoUseCase) :
    ViewModel() {

    val weather: MutableLiveData<WeatherClass> = MutableLiveData()

    fun getCurrTemp(lat: Double, lon: Double) {
        viewModelScope.launch {

            withContext(Dispatchers.Main) {

                when (val response =
                    weatherInfoUseCase.executeUseCase(WeatherInfoRequest(lat, lon))) {

                    is NetworkResponse.Success -> {
                        weather.postValue(response.body)
                    }
                    is NetworkResponse.NetworkError -> {
                    }
                    is NetworkResponse.UnknownError -> {
                        Log.i("response", "" + response)
                    }
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
        listWeatherInfoForDate.isNullOrEmpty().let {
            val map =
                listWeatherInfoForDate?.associateBy(keySelector = { weatherDate ->
                    sdf.parse(
                        weatherDate.dtTxt
                    )
                })
            return map
        }


    }
}