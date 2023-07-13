package com.example.twoviews.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.twoviews.Activity
import com.example.twoviews.R
import com.example.twoviews.data.models.User
import com.example.twoviews.databinding.FragmentLoadingBinding
import com.example.twoviews.ui.viewmodel.LoadingViewModel


class LoadingFragment : Fragment() {
    private lateinit var binding: FragmentLoadingBinding
    private lateinit var mainActivity: Activity
    private val viewModel: LoadingViewModel by viewModels()


    //companion object введён для передачи данных между фрагментами.
    // В данном обжекте сохраняются 2 переменные, полученные из 2 полей ввода данных
    companion object {
        // первая переменная введённая пользователем
        private const val NUMBER1 = "number1"

        // вторая переменная введённая пользователем
        private const val NUMBER2 = "number2"


        //сохранение переменных в Ьандл Аругмент, для передачи в другой фрагмент
        fun newInstance(number1: Long, number2: Long): LoadingFragment {
            val fragment = LoadingFragment()
            val args = Bundle()
            args.putLong(NUMBER1, number1)
            args.putLong(NUMBER2, number2)
            fragment.arguments = args
            return fragment
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

        //получение данных из обоих полей ввода данных. Если содержимое поля пустое, то вернётся 0.
        val number1 = arguments?.getLong(NUMBER1) ?: 0
        val number2 = arguments?.getLong(NUMBER2) ?: 0

        //подсчёт суммы с помощью метода из viewmodel
        viewModel.calculateSum(number1, number2)
        //получение списка пользователей с помощью подписи на liveData viewmodel
        viewModel.usersLiveData.observe(viewLifecycleOwner) { users ->
            showResultFragment(viewModel.sumLiveData.value ?: 0, users)
        }


    }

    // переход на следующий фрагмент с передачей данных сумма цифр и список пользователей
    // так же через логирование проверяем что список пользовай содержит данные
    private fun showResultFragment(sum: Long, users: List<User>) {
        Log.d("LoadingFragment", "Sum: $sum, Users: $users") // Логирование значений
        val fragment = ResultFragment.newInstance(sum, users)
        mainActivity.supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

}