package com.example.twoviews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twoviews.data.models.User
import com.example.twoviews.data.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingViewModel : ViewModel() {

    //создаём переменную и присваиваем ей значений изменяемой liveData
    private val _sumLiveData = MutableLiveData<Int>()


    //создаём переменную и присваиваем ей значений неизменяемой liveData, значений которой мы получаем из переменной _sumLiveData
    val sumLiveData: LiveData<Int> get() = _sumLiveData

    //создаём переменную и присваиваем ей значений изменяемой liveData
    private val _usersLiveData = MutableLiveData<List<User>>()

    //создаём переменную и присваиваем ей значений неизменяемой liveData, значений которой мы получаем из переменной _usersLiveData
    val usersLiveData: LiveData<List<User>> get() = _usersLiveData

    //функция для подсчёта суммы. сумма сохраняется в переменную _sumLiveData.value На изменений которой и подписывается liveData
    // так же здесь мы вызываем функцию loadData() для загрузки списка пользователей
    fun calculateSum(number1: Int, number2: Int) {
        val sum = number1 + number2
        _sumLiveData.value = sum
        loadData()
    }

    //функция для получения списка пользователей из Репозитория. Так же в этой функции прописана имитация задержки на 2 секунды.
    //В этой функции мы получаем список пользователей из Репозитория. Сохраняем его в переменной users. И затем в переменную _usersLiveData.value записываем этот список
    // и затем на изменений этой переменной и подписывается liveData

    private fun loadData() {
        viewModelScope.launch {
            delay(2000) // имитация задержки загрузки данных
            val users = UserRepository.getUsers()
            _usersLiveData.value = users

        }
    }
}