package com.yhl.example.weather.logic.network

import com.yhl.example.weather.logic.network.retrofitutils.BaseUrlInterceptor
import com.yhl.example.weather.logic.network.retrofitutils.CustomGsonConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    val urlMap: HashMap<String, String> = hashMapOf(
            "places" to "https://geoapi.qweather.com/v2/city/lookup?",
            "weather" to "https://devapi.qweather.com/v7/weather/now?")
    private const val BASE_CITY_URL = "https://devapi.qweather.com/"
    private val interceptor = BaseUrlInterceptor()
    private val client by lazy {
        OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
    }

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_CITY_URL)
            .addConverterFactory(CustomGsonConverterFactory.create())
            .client(client)
            .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)

}