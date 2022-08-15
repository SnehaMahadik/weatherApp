package com.mobile.myweatherapp.ui.viewModels.viewModel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mobile.myweatherapp.domain.WeatherInfoUseCase
import com.mobile.myweatherapp.domain.WeatherInfoUseCaseImpl
import com.mobile.myweatherapp.models.City
import com.mobile.myweatherapp.models.List
import com.mobile.myweatherapp.models.WeatherClass
import com.mobile.myweatherapp.networking.remote.NetworkResponse
import com.mobile.myweatherapp.domain.repo.DefaultRepository
import com.mobile.myweatherapp.domain.repo.WeatherRepository
import com.mobile.myweatherapp.presentation.currentWeather.WeatherViewModel
import com.mobile.myweatherapp.ui.viewModels.data.FakeWeatherRepository
import com.mobile.myweatherapp.ui.viewModels.utils.TestCoroutineRule
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response.success
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
    fun `test getWeather for success response`() {
        testCoroutineRule.runBlockingTest {
            weatherViewModel = WeatherViewModel(weatherInfoUseCase)
            weatherViewModel.getCurrTemp(89.0, 90.98)
            weatherViewModel.weather.observeForever(Observer { apiUsersObserver })
            val response = success(getMockResponse())
            assertEquals(response.body()?.city?.name, "London")
        }
    }

    @Test
    fun `test getWeather for api error response`() {
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