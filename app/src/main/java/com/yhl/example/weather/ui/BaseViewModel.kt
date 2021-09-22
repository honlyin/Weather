package com.yhl.example.weather.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yhl.example.weather.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    companion object {
        private const val TAG = "BaseViewModel"
    }

    val errorLiveData = MutableLiveData<Throwable>()

    fun lunch(
            block: suspend () -> Unit,
            error: suspend (Throwable) -> Unit,
            complete: suspend () -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                LogUtils.d(TAG, Thread.currentThread().name)
                block()
            } catch (e: Exception) {
                error(e)
            } finally {
                complete()
            }
        }
    }
}