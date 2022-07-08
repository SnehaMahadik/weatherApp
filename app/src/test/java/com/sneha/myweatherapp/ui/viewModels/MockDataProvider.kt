package com.sneha.myweatherapp.ui.viewModels

import com.sneha.myweatherapp.modals.List


fun getMockWeatherList(): ArrayList<List> {
    return arrayListOf(List(dtTxt = "12/04/1990"))
}
