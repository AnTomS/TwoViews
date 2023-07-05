package com.example.twoviews

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.twoviews.databinding.ActivityMainBinding
import com.example.twoviews.models.User
import com.example.twoviews.screens.InputFragment
import com.example.twoviews.screens.LoadingFragment
import com.example.twoviews.screens.ResultFragment

class Activity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.commit {
            add(R.id.container, InputFragment())
        }
        Log.d("Activity", "onCreate")


    }

    fun navigateToLoadingFragment() {
        supportFragmentManager.commit {
            replace(R.id.container, LoadingFragment())
        }
    }


    // Метод для перехода к третьему фрагменту (ResultFragment)
    fun navigateToResultFragment(sum: Int, users: List<User>) {
        supportFragmentManager.commit {
            replace(R.id.container, ResultFragment.newInstance(sum, users))
        }


    }
}