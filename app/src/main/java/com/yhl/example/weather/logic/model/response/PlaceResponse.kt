package com.yhl.example.weather.logic.model.response

import com.google.gson.annotations.SerializedName

data class PlaceResponse(@SerializedName("location") override val data: List<Place>) : BaseResponse<List<Place>>()

data class Place(
        val name: String, val id: Int, val lat: Double, val lon: Double,
        val country: String, @SerializedName("tz") val timeZone: String,
        val source: String?, val license: String?,
)
