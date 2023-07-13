package com.example.twoviews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.twoviews.data.models.User
import com.example.twoviews.ui.screens.InputFragment
import com.example.twoviews.ui.screens.LoadingFragment
import com.example.twoviews.ui.screens.ResultFragment

class Activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showInputFragment()

    }

    //переход на фрагмент ввода цифр с помощью supportFragmentManager
    fun showInputFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, InputFragment.newInstance())
            .addToBackStack(null)
            .commit()

    }

    //переход на фрагмент экрана загрузки, подсчёта суммы и запроса списка пользователей
    fun showLoadingFragment(number1: Long, number2: Long) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, LoadingFragment.newInstance(number1, number2))
            .commit()
    }

    //переход на экран результата
    fun showResultFragment(sum: Long, users: List<User>) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, ResultFragment.newInstance(sum, users))
            .commit()
    }
}