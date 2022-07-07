package com.sneha.myweatherapp.ui.viewModels.viewModel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sneha.myweatherapp.domain.WeatherInfoUseCase
import com.sneha.myweatherapp.domain.WeatherInfoUseCaseImpl
import com.sneha.myweatherapp.modals.City
import com.sneha.myweatherapp.modals.List
import com.sneha.myweatherapp.modals.WeatherClass
import com.sneha.myweatherapp.modals.WeatherInfoRequest
import com.sneha.myweatherapp.networking.remote.NetworkResponse
import com.sneha.myweatherapp.repo.DefaultRepository
import com.sneha.myweatherapp.repo.WeatherRepository
import com.sneha.myweatherapp.ui.viewModels.WeatherViewModel
import com.sneha.myweatherapp.ui.viewModels.data.FakeWeatherRepository
import com.sneha.myweatherapp.ui.viewModels.utils.TestCoroutineRule
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyObject
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response.success
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherViewModelTest {

    lateinit var weatherViewModel: WeatherViewModel

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var weatherRepository: WeatherRepository

    @Mock
    private lateinit var apiUsersObserver: Observer<WeatherClass>
    private lateinit var fakeWeatherRepository: DefaultRepository
    private lateinit var weatherInfoUseCase: WeatherInfoUseCase

    @Before
    fun setUp() {
        fakeWeatherRepository = FakeWeatherRepository()
        weatherInfoUseCase = WeatherInfoUseCaseImpl(fakeWeatherRepository)
    }

    @Test
    fun getWeather() {
        testCoroutineRule.runBlockingTest {
            weatherViewModel = WeatherViewModel(weatherInfoUseCase)
            weatherViewModel.getCurrTemp(89.0, 90.98)
            weatherViewModel.weather.observeForever(apiUsersObserver)
            val response = success(getMockResponse())
            assertEquals(response.body()?.city?.name, "London")
        }
    }

    @Test
    fun getWeather_ApiError() {
        (fakeWeatherRepository as FakeWeatherRepository).setApiResponse(false)
        testCoroutineRule.runBlockingTest {
            weatherViewModel = WeatherViewModel(weatherInfoUseCase)
            weatherViewModel.getCurrTemp(1.1, 9.3)
            val errorCode = NetworkResponse.ApiError(
                code = 503,
                body = null
            ).code
            assertEquals(503, errorCode)
        }
    }

    private fun getMockResponse(): WeatherClass {
        val list: ArrayList<List> = arrayListOf()
        list.add(List(dtTxt = "12/4/10"))
        return WeatherClass(list = list, city = City(name = "London"))
    }
}