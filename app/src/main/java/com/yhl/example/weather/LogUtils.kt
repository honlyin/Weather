package com.yhl.example.weather

import android.util.Log

object LogUtils {
    private const val TAG = "Weather_"

    public fun i(tag: String, msg: String) {
        Log.i(TAG + tag, msg)
    }

    public fun i(msg: String) {
        Log.i(TAG, msg)
    }

    public fun d(tag: String, msg: String) {
        Log.d(TAG + tag, msg)
    }

    public fun d(msg: String) {
        Log.d(TAG, msg)
    }

    public fun v(tag: String, msg: String) {
        Log.v(TAG + tag, msg)
    }

    public fun v(msg: String) {
        Log.v(TAG, msg)
    }

    public fun w(tag: String, msg: String) {
        Log.w(TAG + tag, msg)
    }

    public fun w(msg: String) {
        Log.w(TAG, msg)
    }

    public fun e(tag: String, msg: String) {
        Log.e(TAG + tag, msg)
    }

    public fun e(msg: String) {
        Log.e(TAG, msg)
    }
}