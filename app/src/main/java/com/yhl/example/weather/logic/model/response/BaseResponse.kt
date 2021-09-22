package com.yhl.example.weather.logic.model.response

abstract class BaseResponse<T>(val code: Int = 0, open val data: T? = null)
