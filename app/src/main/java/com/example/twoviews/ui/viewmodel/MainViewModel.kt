package com.example.twoviews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twoviews.data.models.User
import com.example.twoviews.data.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

open class MainViewModel : ViewModel() {

    //создание изменяемой переменной с помощью мутабельного состояния, на отслеживание которого подписываеися  Composable функция,
    // для отслеживания 1 ввёденого числа, в качестве дефолтного значения выбран не 0, а пустое поле
    private val _number1 = MutableLiveData<String>("")

    //создание неизменяемой переменной для отслеживания и доступа 1 ввёденого числа
    val number1: LiveData<String> get() = _number1

    //создание изменяемой переменной с помощью мутабельного состояния, на отслеживание которого подписываеися  Composable функция,
    // для отслеживания 2 ввёденого числа, в качестве дефолтного значения выбран не 0, а пустое поле
    private val _number2 = MutableLiveData<String>("")

    //создание неизменяемой переменной для отслеживания и доступа к 2 ввёденого числа
    val number2: LiveData<String> get() = _number2

    //создаём переменную и присваиваем ей значений изменяемой liveData
    private val _sumLiveData = MutableLiveData<Long>()

    //создаём переменную и присваиваем ей значений неизменяемой liveData, значений которой мы получаем из переменной _sumLiveData
    val sumLiveData: LiveData<Long> get() = _sumLiveData


    //создаём переменную и присваиваем ей значений изменяемой liveData
    private val _usersLiveData = MutableLiveData<List<User>>()

    //создаём переменную и присваиваем ей значений неизменяемой liveData, значений которой мы получаем из переменной _usersLiveData
    val usersLiveData: LiveData<List<User>> get() = _usersLiveData


    fun calculateSum(number1: Long, number2: Long) {
        val sum = number1 + number2
        _sumLiveData.value = sum
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            delay(2000) // имитация задержки загрузки данных
            val users = UserRepository.getUsers()
            _usersLiveData.value = users
        }
    }

    //функция для отслеживания 1 ввёденого числа.

    fun updateNumber1(value: String) {
        _number1.value = value
    }

    //функция для отслеживания 2 ввёденого числа.
    fun updateNumber2(value: String) {
        _number2.value = value
    }

    //функция для вычисления суммы чисел и сохранения в переменную
    fun updateSum() {
        val number1 = _number1.value?.toLongOrNull() ?: 0L
        val number2 = _number2.value?.toLongOrNull() ?: 0L
        _sumLiveData.value = number1 + number2
    }

    //функция для запроса списка пользователей и сохранение его в переменную
    fun getUsersFromRepository() {
        val users = UserRepository.getUsers()
        _usersLiveData.value = users
    }

    //функция для првоерки полей ввода. Если поля не пустые, то возвращается true, иначе false
    //если возвращает false, то пользователь не сможет нажать на кнопку
    fun isValidInput(): Boolean {
        return number1.value?.isNotEmpty() == true && number2.value?.isNotEmpty() == true
    }

}