package com.yhl.example.weather.logic.network

import com.yhl.example.weather.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object Repository {
    private const val TAG = "Repository"
    private val retrofitService = ServiceCreator.create(RetrofitService::class.java)

    suspend fun searchPlaces(query: String) = retrofitService.searchPlaces(query)
}