package com.sneha.myweatherapp.viewModels


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sneha.myweatherapp.modals.City
import com.sneha.myweatherapp.modals.List
import com.sneha.myweatherapp.modals.WeatherClass
import com.sneha.myweatherapp.repo.WeatherRepository
import com.sneha.myweatherapp.viewModels.utils.TestCoroutineRule
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.Result
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import retrofit2.Response.success

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherViewModelTest {

    lateinit var weatherViewModel : WeatherViewModel
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var weatherRepository: WeatherRepository
    @Mock
    private lateinit var apiUsersObserver: Observer<WeatherClass>

    @Before
    fun setUp() {

    }

    @Test
    fun getWeather() {
        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<WeatherClass>())
                .`when`(weatherRepository)
                .getCurrentWeather(89.0,90.98)
            val viewModel = WeatherViewModel(weatherRepository)
            viewModel.getCurrTemp(89.0,90.98)
            viewModel.weather.observeForever(apiUsersObserver)
            val response = success(getMockResponse())
            assertEquals(response.body()?.city?.name, "London")
        }
    }

    private fun getMockResponse(): WeatherClass {
        val list    : ArrayList<List> = arrayListOf()
        list.add(List(dtTxt = "12/4/10"))
        return WeatherClass(list=list,city = City(name = "London"))
    }
}