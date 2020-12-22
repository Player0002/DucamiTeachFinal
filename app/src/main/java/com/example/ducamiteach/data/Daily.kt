package com.example.ducamiteach.data

data class Daily(
    val clouds: Int,
    val dew_point: Double,
    val dt: Int,
    val feels_like: FeelsLike,
    val humidity: Double,
    val pop: Double,
    val pressure: Double,
    val rain: Double,
    val snow: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val uvi: Double,
    val weather: List<WeatherXX>,
    val wind_deg: Double,
    val wind_speed: Double
)