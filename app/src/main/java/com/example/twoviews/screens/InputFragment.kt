package com.example.twoviews.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.twoviews.Activity
import com.example.twoviews.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    private lateinit var binding: FragmentInputBinding

    private lateinit var mainActivity: Activity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity = activity as Activity

        binding.btnNextView.setOnClickListener {
            val number1 = binding.inputN1.text.toString().toInt()
            val number2 = binding.inputN2.text.toString().toInt()
            val sum = number1 + number2

          mainActivity.navigateToResultFragment(sum, emptyList())
        }
    }
}
