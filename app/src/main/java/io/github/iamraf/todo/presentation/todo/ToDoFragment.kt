package io.github.iamraf.todo.presentation.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.iamraf.todo.databinding.FragmentTodoBinding
import io.github.iamraf.todo.domain.entity.ToDo

@AndroidEntryPoint
class ToDoFragment : Fragment(), ToDoAdapter.ToDoAdapterListener {
    private val viewModel: ToDoViewModel by viewModels()
    private val toDoAdapter: ToDoAdapter by lazy { ToDoAdapter(this) }

    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTodoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        with(binding) {
            recycler.apply {
                setHasFixedSize(true)
                adapter = toDoAdapter
            }
        }
    }

    private fun setupObservers() {
        with(viewModel) {
            list.observe(viewLifecycleOwner, {
                toDoAdapter.submitList(it)
            })

            error.observe(viewLifecycleOwner, {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })

            fetchTodo()
        }
    }

    override fun onClick(item: ToDo) {
        // Handle click
    }
}