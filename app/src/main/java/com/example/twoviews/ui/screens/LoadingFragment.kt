package com.example.twoviews.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.twoviews.Activity
import com.example.twoviews.databinding.FragmentLoadingBinding
import com.example.twoviews.ui.viewmodel.MainViewModel


class LoadingFragment : Fragment() {
    private lateinit var binding: FragmentLoadingBinding
    private lateinit var mainActivity: Activity
    private val viewModel: MainViewModel by activityViewModels()

    //companion object введён для навигации между фрагментами.

    companion object {
        fun newInstance(): LoadingFragment {

            return LoadingFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoadingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //инициализация Активити, для перехода в другой фрагмент
        mainActivity = activity as Activity

        // Получение данных из обоих полей ввода данных. Если содержимое поля пустое, то вернётся 0.
        val number1 = viewModel.number1.value?.toLongOrNull() ?: 0
        val number2 = viewModel.number2.value?.toLongOrNull() ?: 0

        //подсчёт суммы с помощью метода из viewmodel
        viewModel.calculateSum(number1, number2)
        //получение списка пользователей с помощью подписи на liveData viewmodel
        viewModel.usersLiveData.observe(viewLifecycleOwner) { users ->
            mainActivity.showResultFragment()
        }

    }


}