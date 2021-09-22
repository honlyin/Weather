package com.yhl.example.weather.logic.network

import com.yhl.example.weather.WeatherApplication
import com.yhl.example.weather.logic.model.response.PlaceResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitService {
    @Headers("query:places")
    @GET("v2")
    suspend fun searchPlaces(@Query("location") location: String,
                     @Query("key") key: String = WeatherApplication.TOKEN_HF): PlaceResponse

}