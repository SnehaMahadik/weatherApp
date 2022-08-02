package com.mobile.myweatherapp.ui.viewModels

import com.mobile.myweatherapp.models.List


fun getMockWeatherList(): ArrayList<List> {
    return arrayListOf(List(dtTxt = "12/04/1990"))
}
