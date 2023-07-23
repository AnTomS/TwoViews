package com.example.twoviews.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.twoviews.Activity
import com.example.twoviews.databinding.FragmentInputBinding
import com.example.twoviews.ui.viewmodel.MainViewModel


class InputFragment : Fragment() {

    private lateinit var binding: FragmentInputBinding

    private lateinit var mainActivity: Activity

    private val viewModel: MainViewModel by activityViewModels()

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

        //инициализация активити , понадобится для перехода в другой фрагмент
        mainActivity = activity as Activity

        //для кнопки перехода на следующий фрагмент задаётся состояние false, которое делает кнопку неактивной
        binding.btnNextView.isEnabled = false

        //проверка 1 поля ввода, содержит ли оно число, с помощью слушателя изменения текста и проверки на нул
        // Если поле ввода непустое, то кнопка перехода на следующий фрагмент становится активной
        binding.inputN1.addTextChangedListener { text ->
            val number1 = text.toString()


            val number2 = binding.inputN2.text.toString()


            binding.btnNextView.isEnabled = number1.isNotEmpty() && number2.isNotEmpty()
            viewModel.updateNumber1(number1)
        }
        //проверка 2 поля ввода, содержит ли оно число, с помощью слушателя изменения текста и проверки на нул.
        // Если поле ввода непустое, то кнопка перехода на следующий фрагмент становится активной
        binding.inputN2.addTextChangedListener { text ->
            val number1 = binding.inputN1.text.toString()
            val number2 = text.toString()
            binding.btnNextView.isEnabled = number1.isNotEmpty() && number2.isNotEmpty()
            viewModel.updateNumber2(number2)
        }

        //обработка клика на кнопку перехода на след фрагмент с передачей данных
        binding.btnNextView.setOnClickListener {
            val number1 = binding.inputN1.text.toString().toLong()
            val number2 = binding.inputN2.text.toString().toLong()
            mainActivity.showLoadingFragment()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("InputFragment", "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("InputFragment", "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("InputFragment", "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("InputFragment", "onStop() called")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("InputFragment", "onDestroyView() called")
    }
}
