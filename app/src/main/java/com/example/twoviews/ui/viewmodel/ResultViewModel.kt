package com.example.twoviews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twoviews.data.models.User

class ResultViewModel : ViewModel() {


    //создаём переменную и присваиваем ей значений изменяемой liveData
    private val _sumLiveData = MutableLiveData<Long>()


    //создаём переменную и присваиваем ей значений неизменяемой liveData, значений которой мы получаем из переменной _sumLiveData
    val sumLiveData: LiveData<Long> get() = _sumLiveData

    //создаём переменную и присваиваем ей значений изменяемой liveData
    private val _usersLiveData = MutableLiveData<List<User>>()

    //создаём переменную и присваиваем ей значений неизменяемой liveData, значений которой мы получаем из переменной _usersLiveData
    val usersLiveData: LiveData<List<User>> get() = _usersLiveData


    //метод для получения суммы. сумма сохраняется в переменную _sumLiveData.value На изменений которой и подписывается liveData
    fun setSum(sum: Long) {
        _sumLiveData.value = sum
    }

    // Метод для получения списка пользователей. Сохраняем его в переменную _usersLiveData.value. На изменений которой и подписывается liveData
    fun setUsers(users: List<User>) {
        _usersLiveData.value = users
    }
}




