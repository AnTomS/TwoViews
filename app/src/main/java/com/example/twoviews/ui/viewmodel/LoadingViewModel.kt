package com.example.twoviews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoadingViewModel:ViewModel() {
    private val _sumLiveData = MutableLiveData<Int>()
    val sumLiveData: LiveData<Int> get() = _sumLiveData

    fun calculateSum(number1: Int, number2: Int) {
        val sum = number1 + number2
        _sumLiveData.value = sum
    }
}