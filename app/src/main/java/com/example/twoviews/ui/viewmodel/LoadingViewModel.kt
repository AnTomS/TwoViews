package com.example.twoviews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twoviews.data.models.User
import com.example.twoviews.data.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingViewModel:ViewModel() {
    private val _sumLiveData = MutableLiveData<Int>()
    val sumLiveData: LiveData<Int> get() = _sumLiveData
    private val _usersLiveData = MutableLiveData<List<User>>()
    val usersLiveData: LiveData<List<User>> get() = _usersLiveData

    fun calculateSum(number1: Int, number2: Int) {
        val sum = number1 + number2
        _sumLiveData.value = sum
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            delay(2000) // имитация задержки загрузки данных
            val users = UserRepository.getUsers()
            _usersLiveData.value = users

        }
    }
}