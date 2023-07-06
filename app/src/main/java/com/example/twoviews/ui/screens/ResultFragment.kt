package com.example.twoviews.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.twoviews.Activity
import com.example.twoviews.data.models.User
import com.example.twoviews.databinding.FragmentResultBinding
import com.example.twoviews.ui.viewmodel.ResultViewModel

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private val viewModel: ResultViewModel by viewModels()
    private lateinit var mainActivity: Activity


    companion object {
        private const val ARG_SUM = "arg_sum"
        private const val ARG_USERS = "arg_users"

        fun newInstance(sum: Int, users: List<User>): ResultFragment {
            val fragment = ResultFragment()
            val args = Bundle()
            args.putInt(ARG_SUM, sum)
//            args.putParcelableArrayList(ARG_USERS, List<User>)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)

        mainActivity = activity as Activity
        binding.btnClose.setOnClickListener {
            mainActivity.finish()
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        if (args != null) {
            val sum = args.getInt(ARG_SUM)
            viewModel.setSum(sum)
        }

        viewModel.sumLiveData.observe(viewLifecycleOwner, { sum ->
            binding.resultSum.text = sum.toString()
        })

        viewModel.usersLiveData.observe(viewLifecycleOwner, { users ->
            // Отображение списка пользователей
            // ...
        })
    }
}

