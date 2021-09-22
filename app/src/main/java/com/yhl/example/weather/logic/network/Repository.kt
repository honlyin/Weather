package com.yhl.example.weather.logic.network

object Repository {
    private const val TAG = "Repository"
    private val retrofitService = ServiceCreator.create(RetrofitService::class.java)

    suspend fun searchPlaces(query: String) = retrofitService.searchPlaces(query)
}