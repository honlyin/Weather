package com.yhl.example.weather.ui

import androidx.lifecycle.*
import com.yhl.example.weather.LogUtils
import com.yhl.example.weather.logic.model.response.Place
import com.yhl.example.weather.logic.network.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlaceViewModel : BaseViewModel() {
    companion object {
        private const val TAG = "PlaceViewModel"
    }

    private val _placeLiveData = MutableLiveData<List<Place>>()

    val placeLiveData: LiveData<List<Place>> = _placeLiveData

    val placeList = ArrayList<Place>()

    fun searchPlaces(query: String) {
        lunch(
                {
                    val result = withContext(Dispatchers.IO) {
                        LogUtils.d(TAG, Thread.currentThread().name)
                        Repository.searchPlaces(query)
                    }
                    _placeLiveData.value = result.data
                },
                {
                    LogUtils.e(TAG, it.toString())
                    errorLiveData.value = it
                },
                {

                }
        )
    }
}