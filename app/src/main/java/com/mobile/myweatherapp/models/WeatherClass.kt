package com.mobile.myweatherapp.models

import com.google.gson.annotations.SerializedName
import com.mobile.myweatherapp.models.City
import com.mobile.myweatherapp.models.List
import java.io.Serializable

 class WeatherClass (
     @SerializedName("list"    ) var list    : ArrayList<List> = arrayListOf(),
     @SerializedName("city"    ) var city    : City?           = City()
): Serializable