package com.yhl.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.yhl.example.weather.logic.model.response.Place
import com.yhl.example.weather.ui.PlaceViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private val viewModel by viewModels<PlaceViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        query_tv.setOnClickListener {
            viewModel.searchPlaces("beijing")
            LogUtils.d(TAG, "onCreate: ")
        }

        viewModel.placeLiveData.observe(this, {
            if (it != null) {
                viewModel.placeList.clear();
                viewModel.placeList.addAll(it)
                LogUtils.d(TAG, "size = " + viewModel.placeList.size)
            }
        })
    }

}