package com.example.twoviews.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.twoviews.Activity
import com.example.twoviews.data.models.User
import com.example.twoviews.databinding.FragmentResultBinding
import com.example.twoviews.ui.adapter.ResultAdapter
import com.example.twoviews.ui.viewmodel.ResultViewModel

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private val viewModel: ResultViewModel by viewModels()
    private lateinit var mainActivity: Activity
    private lateinit var adapter: ResultAdapter


    companion object {
        private const val ARG_SUM = "arg_sum"
        private const val ARG_USERS = "arg_users"

        fun newInstance(sum: Int, users: List<User>): ResultFragment {
            val fragment = ResultFragment()
            val args = Bundle()
            args.putInt(ARG_SUM, sum)
            args.putParcelableArrayList(ARG_USERS, ArrayList(users))
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
            @Suppress("DEPRECATION")
            val users = args.getParcelableArrayList<User>(ARG_USERS)?.toList() ?: emptyList()
            viewModel.setSum(sum)
            Log.d("TAG", "onViewCreated: $sum")

            viewModel.setUsers(users)
            Log.d("TAG", "onViewCreated: $users")

            adapter = ResultAdapter(users)
            binding.rvList.adapter = adapter
            binding.rvList.layoutManager = LinearLayoutManager(requireContext())
        }


        viewModel.sumLiveData.observe(viewLifecycleOwner) { sum ->
            binding.resultSum.text = sum.toString()
        }

        viewModel.usersLiveData.observe(viewLifecycleOwner) { users ->
            adapter.submitList(users)
            Log.d("ResultFragment", "Users: $users")
        }
    }
}

