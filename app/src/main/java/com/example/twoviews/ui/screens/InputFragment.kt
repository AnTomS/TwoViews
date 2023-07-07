package com.example.twoviews.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.twoviews.Activity
import com.example.twoviews.databinding.FragmentInputBinding


class InputFragment : Fragment() {

    private lateinit var binding: FragmentInputBinding

    private lateinit var mainActivity: Activity

    companion object {
        fun newInstance(): InputFragment {
            return InputFragment()
        }
    }

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

        binding.btnNextView.isEnabled = false

        binding.inputN1.addTextChangedListener { text ->
            val number1 = text?.toString()?.toIntOrNull()
            val number2 = binding.inputN2.text.toString().toIntOrNull()
            binding.btnNextView.isEnabled = number1 != null && number2 != null
        }

        binding.inputN2.addTextChangedListener { text ->
            val number1 = binding.inputN1.text.toString().toIntOrNull()
            val number2 = text?.toString()?.toIntOrNull()
            binding.btnNextView.isEnabled = number1 != null && number2 != null
        }

        binding.btnNextView.setOnClickListener {
            val number1 = binding.inputN1.text.toString().toInt()
            val number2 = binding.inputN2.text.toString().toInt()
            mainActivity.showLoadingFragment(number1, number2)
        }
    }
}
