package com.yhl.example.weather.logic.network.retrofitutils

import com.yhl.example.weather.LogUtils
import com.yhl.example.weather.logic.network.ServiceCreator
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class BaseUrlInterceptor : Interceptor {
    companion object {
        private const val TAG = "BaseUrlInterceptor"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        //获取request
        val request = chain.request()
        //从request中获取原有的HttpUrl实例oldHttpUrl
        val oldHttpUrl = request.url()
        LogUtils.d(TAG, "intercept: old url = $oldHttpUrl")
        //获取request的创建者builder
        val builder = request.newBuilder()
        //从request中获取headers,通过给定的键url_name
        val headersValues: List<String>? = request.headers("query")
        if (headersValues != null && headersValues.isNotEmpty()) {
            //删除header
            builder.removeHeader("urlName");
            //匹配获得新的BaseUrl
            val newBaseUrl: HttpUrl = HttpUrl.parse(
                    ServiceCreator.urlMap[headersValues[0]]
                            ?: "https://devapi.qweather.com/v7/weather/now?"
            ) ?: oldHttpUrl
            val newFullUrl = oldHttpUrl
                    .newBuilder()
                    .scheme("https")
                    .host(newBaseUrl.host())
                    .port(newBaseUrl.port())
                    .removePathSegment(0)
                    .addPathSegments(newBaseUrl.encodedPath().substring(1))
                    .build()
            LogUtils.d(TAG, "intercept: request url = $newFullUrl")
            return chain.proceed(builder.url(newFullUrl).build())
        }

        return chain.proceed(request)
    }
}