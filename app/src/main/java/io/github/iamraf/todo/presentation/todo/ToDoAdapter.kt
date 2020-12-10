package io.github.iamraf.todo.presentation.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.iamraf.todo.databinding.ItemTodoBinding
import io.github.iamraf.todo.domain.entity.ToDo

class ToDoAdapter(private val listener: ToDoAdapterListener) : ListAdapter<ToDo, ToDoAdapter.ToDoViewHolder>(DiffUtilCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTodoBinding.inflate(inflater, parent, false)

        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ToDoViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                if (adapterPosition < 0) return@setOnClickListener

                getItem(adapterPosition)?.let {
                    listener.onClick(it)
                }
            }
        }

        fun bind(item: ToDo) {
            with(binding) {
                description.text = item.description
                checkBox.isChecked = item.completed
            }
        }
    }

    companion object DiffUtilCallback : DiffUtil.ItemCallback<ToDo>() {
        override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
            return oldItem.description == newItem.description
        }

        override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
            return oldItem == newItem
        }
    }

    interface ToDoAdapterListener {
        fun onClick(item: ToDo)
    }
}