package com.sneha.myweatherapp.networking


import com.sneha.myweatherapp.modals.WeatherClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherInstance {

    companion object{
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }

    @GET("forecast")
    suspend fun getCurrentTemp(@Query("appid")key : String , @Query("lat")lat : Double , @Query("lon")lon : Double) : Response<WeatherClass>
}
