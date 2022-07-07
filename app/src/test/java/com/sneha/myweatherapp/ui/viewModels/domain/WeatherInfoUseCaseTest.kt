package com.sneha.myweatherapp.ui.viewModels.domain

import com.sneha.myweatherapp.domain.WeatherInfoUseCase
import com.sneha.myweatherapp.domain.WeatherInfoUseCaseImpl
import com.sneha.myweatherapp.modals.City
import com.sneha.myweatherapp.modals.WeatherClass
import com.sneha.myweatherapp.modals.WeatherInfoRequest
import com.sneha.myweatherapp.networking.remote.NetworkResponse
import com.sneha.myweatherapp.networking.remote.NetworkResponse.Success
import com.sneha.myweatherapp.repo.DefaultRepository
import com.sneha.myweatherapp.ui.viewModels.data.FakeWeatherRepository
import com.sneha.myweatherapp.ui.viewModels.getMockWeatherList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

@ExperimentalCoroutinesApi
class WeatherInfoUseCaseTest {
    private lateinit var weatherInfoUseCase : WeatherInfoUseCase
    private lateinit var fakeWeatherRepository : DefaultRepository

    @Before
    fun setup(){
        fakeWeatherRepository = FakeWeatherRepository()
        weatherInfoUseCase = WeatherInfoUseCaseImpl(fakeWeatherRepository)
    }


    @Test
    fun `get weather api success`(){
        runBlockingTest {
            val weatherResponse = weatherInfoUseCase.executeUseCase(WeatherInfoRequest(2.0,4.0))
            val date = (weatherResponse as Success).body.list[0].dtTxt
            assertEquals( Success(WeatherClass(list = getMockWeatherList(),city = City())).body.list[0].dtTxt, date )
        }
    }
    @Test
    fun `get weather api error response`(){
        (fakeWeatherRepository as FakeWeatherRepository).setApiResponse(false)
        runBlockingTest {
            val weatherResponse = weatherInfoUseCase.executeUseCase(WeatherInfoRequest(0.0,0.0))
            val errorCode = (weatherResponse as NetworkResponse.ApiError).code
            assertEquals(NetworkResponse.ApiError(code = 503,body = null).code,errorCode)
        }
    }
}