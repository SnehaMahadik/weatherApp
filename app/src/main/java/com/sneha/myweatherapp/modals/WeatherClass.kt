package com.sneha.myweatherapp.modals

import com.google.gson.annotations.SerializedName
import java.io.Serializable

 class WeatherClass (
     @SerializedName("list"    ) var list    : ArrayList<List> = arrayListOf(),
     @SerializedName("city"    ) var city    : City?           = City()
): Serializable