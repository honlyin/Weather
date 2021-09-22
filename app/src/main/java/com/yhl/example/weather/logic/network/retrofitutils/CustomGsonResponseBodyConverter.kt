package com.yhl.example.weather.logic.network.retrofitutils

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.yhl.example.weather.logic.model.response.PlaceResponse
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import kotlin.text.Charsets.UTF_8

class CustomGsonResponseBodyConverter<T>(private val gson: Gson, private val adapter: TypeAdapter<T>) : Converter<ResponseBody, T> {
    companion object {

        private const val TAG = "Converter"
    }

    override fun convert(value: ResponseBody): T? {
        val response = value.string()
        val httpStatus = gson.fromJson(response, PlaceResponse::class.java)
        //验证status返回是否为1
        if (httpStatus.code == 1) {
            value.close()
            //不为-1，表示响应数据不正确，抛出异常
            return null
        }

        //继续处理body数据反序列化，注意value.string() 不可重复使用
        val contentType = value.contentType()
        val charset = if (contentType != null) contentType.charset(UTF_8) else UTF_8
        val inputStream = ByteArrayInputStream(response.toByteArray())
        val reader = InputStreamReader(inputStream, charset!!)
        val jsonReader = gson.newJsonReader(reader)

        try {
            return adapter.read(jsonReader)
        } finally {
            value.close()
        }
    }
}