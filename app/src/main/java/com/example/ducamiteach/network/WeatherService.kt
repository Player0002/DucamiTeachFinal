package com.example.ducamiteach.network

import com.example.ducamiteach.data.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("onecall")
    suspend fun getWeather(
        @Query("lat") lat : Double,
        @Query("lon") lon : Double,
        @Query("appid") apiKey : String,
        @Query("exclude") exclude : String = "hourly,minutely",
        @Query("units") units : String = "metric",
        @Query("lang") lang : String = "kr"
    ) : Response<Weather>
}