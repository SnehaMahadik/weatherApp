package com.mobile.myweatherapp.networking


import com.mobile.myweatherapp.models.WeatherClass
import com.mobile.myweatherapp.networking.remote.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherInstance {

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }

    @GET("forecast")
    suspend fun getCurrentTemp(
        @Query("appid") key: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): NetworkResponse<WeatherClass, Error>
}
