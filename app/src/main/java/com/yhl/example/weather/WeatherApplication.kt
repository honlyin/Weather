package com.yhl.example.weather

import android.app.Application
import android.content.Context

class WeatherApplication : Application() {
    companion object{
        const val TOKEN = "Vcq3MK5f9yS0B3El" //彩云天气令牌
        const val TOKEN_HF = "307006a581434e8db0ffda02d515716a" //和风天气
        lateinit var context : Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}