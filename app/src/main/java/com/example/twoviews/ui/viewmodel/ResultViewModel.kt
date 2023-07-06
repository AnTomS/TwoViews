package com.example.twoviews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twoviews.data.models.User

class ResultViewModel : ViewModel() {
    private val _sumLiveData = MutableLiveData<Int>()
    val sumLiveData: LiveData<Int> get() = _sumLiveData

    private val _usersLiveData = MutableLiveData<List<User>>()
    val usersLiveData: LiveData<List<User>> get() = _usersLiveData

    fun setSum(sum: Int) {
        _sumLiveData.value = sum
    }

    // Метод для установки списка пользователей
    fun setUsers(users: List<User>) {
        _usersLiveData.value = users
    }
}


//    fun loadData() {
//        viewModelScope.launch {
//            showLoadingFragment()
//            delay(2000) // имитация задержки загрузки данных
//            val users = UserRepository.getUsers()
//            _usersLiveData.value = users
//            showResultFragment(users)
//        }
//    }

