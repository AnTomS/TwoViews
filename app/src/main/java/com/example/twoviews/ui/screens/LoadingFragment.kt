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

    companion object {
        private const val NUMBER1 = "number1"
        private const val NUMBER2 = "number2"

        fun newInstance(number1: Int, number2: Int): LoadingFragment {
            val fragment = LoadingFragment()
            val args = Bundle()
            args.putInt(NUMBER1, number1)
            args.putInt(NUMBER2, number2)
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

        mainActivity = activity as Activity

        val number1 = arguments?.getInt(NUMBER1) ?: 0
        val number2 = arguments?.getInt(NUMBER2) ?: 0

        viewModel.calculateSum(number1, number2)
        viewModel.usersLiveData.observe(viewLifecycleOwner) { users ->
            showResultFragment(viewModel.sumLiveData.value ?: 0, users)
        }

        viewModel.sumLiveData.observe(viewLifecycleOwner) { sum ->
            // Передача суммы в следующий фрагмент

        }

    }

    private fun showResultFragment(sum: Int, users: List<User>) {
        Log.d("LoadingFragment", "Sum: $sum, Users: $users") // Логирование значений
        val fragment = ResultFragment.newInstance(sum, users)
        mainActivity.supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

}