package com.example.twoviews.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.twoviews.Activity
import com.example.twoviews.databinding.FragmentResultBinding
import com.example.twoviews.ui.adapter.ResultAdapter
import com.example.twoviews.ui.viewmodel.MainViewModel

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var mainActivity: Activity
    private lateinit var adapter: ResultAdapter

    //companion object введён для навигации между фрагментами.
    companion object {
        fun newInstance(): ResultFragment {
            return ResultFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        mainActivity = activity as Activity
        binding.btnClose.setOnClickListener {
            mainActivity.showInputFragment()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//инициализация адаптера, для отображения списка пользователей
        adapter = ResultAdapter(emptyList())

        //устанавливаем созданный адаптер для RecyclerView
        binding.rvList.adapter = adapter

        //инициализация адаптера, для определения расположения элементов на экране
        binding.rvList.layoutManager = LinearLayoutManager(requireContext())

        //через нашу общую viewmodel мы подписываемся на изменение переменной sum,
        //когда переменная изменит своё знаничение, то адаптер обновит цифру на экране
        viewModel.sumLiveData.observe(viewLifecycleOwner) { sum ->
            binding.resultSum.text = sum.toString()

            //вывод переменной sum на в логах
            Log.d("ResultFragment", "Sum: $sum")
        }


        //через нашу общую viewmodel мы подписываемся на изменение переменной users,
        //когда переменная изменит своё знаничение, то адаптер обновит список пользователей на экране
        //метод submitList используется для передачи нового списка пользователей в RecyclerView для перерисовки нового спсика элементов
        viewModel.usersLiveData.observe(viewLifecycleOwner) { users ->
            adapter.submitList(users)

            //вывод переменной users на в логах
            Log.d("ResultFragment", "Users: $users")
        }
    }
}

