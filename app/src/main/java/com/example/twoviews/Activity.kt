package com.example.twoviews

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.twoviews.ui.screens.InputFragment
import com.example.twoviews.ui.screens.LoadingFragment
import com.example.twoviews.ui.screens.ResultFragment
import com.example.twoviews.ui.viewmodel.MainViewModel

class Activity : AppCompatActivity() {

    private  val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

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
    fun showLoadingFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, LoadingFragment.newInstance())
            .commit()
    }

    //переход на экран результата
    fun showResultFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, ResultFragment.newInstance())
            .commit()
    }

    override fun onStart() {
        super.onStart()
        Log.d("Activity", "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Activity", "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Activity", "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Activity", "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Activity", "onDestroy() called")
    }
}