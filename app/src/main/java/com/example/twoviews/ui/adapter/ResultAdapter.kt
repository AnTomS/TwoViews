package com.example.twoviews.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.twoviews.R
import com.example.twoviews.data.models.User
import com.example.twoviews.databinding.CardviewListBinding


class ResultAdapter(private val users: List<User> ) : ListAdapter<User, ResultAdapter.ResultViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_list, parent, false)
        return ResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
        Log.d("ResultAdapter", "User: $user")
    }

    inner class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardviewListBinding.bind(itemView)

        fun bind(user: User) {
            with(binding) {
                name.text = user.name
                age.text = user.age.toString()
                Log.d("ResultAdapter", "User: $name, $age")
            }
        }
    }
}

class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.name == newItem.name && oldItem.age == newItem.age
    }
}