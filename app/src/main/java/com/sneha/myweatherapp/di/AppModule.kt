package com.sneha.myweatherapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sneha.myweatherapp.domain.WeatherInfoUseCase
import com.sneha.myweatherapp.domain.WeatherInfoUseCaseImpl
import com.sneha.myweatherapp.networking.WeatherInstance
import com.sneha.myweatherapp.repo.DefaultRepository
import com.sneha.myweatherapp.repo.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideWeatherRepository(defaultWeatherRepository: WeatherRepository) : DefaultRepository
            = defaultWeatherRepository

    @Singleton
    @Provides
    fun provideWeatherService(retrofit: Retrofit) : WeatherInstance {
        return retrofit.create(WeatherInstance::class.java)
    }
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(WeatherInstance.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.readTimeout(60, TimeUnit.SECONDS)
        okHttpBuilder.connectTimeout(60, TimeUnit.SECONDS)
        okHttpBuilder.writeTimeout(60, TimeUnit.SECONDS)
        return okHttpBuilder.build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Singleton
    @Provides
    fun provideWeatherInfoUseCase(weatherInfoUseCaseImpl: WeatherInfoUseCaseImpl) : WeatherInfoUseCase
            = weatherInfoUseCaseImpl
}