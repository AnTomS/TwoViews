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

        if (savedInstanceState == null) {
            showInputFragment()
        }

    }

    private fun showInputFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, InputFragment.newInstance())
            .commit()
    }

    fun showLoadingFragment(number1: Int, number2: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, LoadingFragment.newInstance(number1, number2))
            .commit()
    }

    fun showResultFragment(sum: Int, users: List<User>) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ResultFragment.newInstance(sum, users))
            .commit()
    }
}