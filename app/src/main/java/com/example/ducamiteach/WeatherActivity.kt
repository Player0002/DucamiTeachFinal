package com.example.ducamiteach

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ducamiteach.adapter.MainAdapter
import com.example.ducamiteach.network.RetrofitClient
import com.example.ducamiteach.network.WeatherService
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class WeatherActivity : AppCompatActivity() {
    private val lat = 35.6632929
    private val lon = 128.4135717

    lateinit var retrofit: Retrofit
    lateinit var service: WeatherService

    companion object {
        val iconInformation = hashMapOf(
            "Thunderstorm" to R.drawable.storm,
            "Drizzle" to R.drawable.rain,
            "Rain" to R.drawable.rain,
            "Snow" to R.drawable.snow,
            "Clear" to R.drawable.sunny,
            "Clouds" to R.drawable.cloud
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        retrofit = RetrofitClient.getRetrofitInstance()
        service = RetrofitClient.getWeatherService(retrofit)

        main_recycler.layoutManager = LinearLayoutManager(this)

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getWeather(lat, lon, BuildConfig.API_KEY)
            if (response.isSuccessful) {
                response.body()?.let {
                    withContext(Dispatchers.Main) {
                        temp.text = "${it.current.temp}℃"
                        feels_like.text = "체감온도 : ${it.current.feels_like}℃"
                        status.text = it.current.weather[0].description
                        weather_ico.setImageResource(
                            iconInformation[it.current.weather[0].main] ?: R.drawable.sunny
                        )
                        main_recycler.adapter = MainAdapter(it.daily).also {
                            it.notifyDataSetChanged()
                        }
                    }
                }
            }
        }
    }
}