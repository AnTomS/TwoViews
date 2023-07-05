package com.example.twoviews.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.twoviews.databinding.FragmentResultBinding
import com.example.twoviews.models.User

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private var sum: Int = 0
    private lateinit var users: List<User>

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        if (args != null) {
            sum = args.getInt(ARG_SUM)
            //           users = args.getParcelableArrayList(ARG_USERS) ?: emptyList()
        }

        if (::binding.isInitialized) {
            binding.resultSum.text = sum.toString()
            // Отображение списка пользователей
            // ...
        }
    }
}
