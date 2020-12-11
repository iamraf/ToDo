package io.github.iamraf.todo.presentation.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import io.github.iamraf.todo.R
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

            fab.setOnClickListener {
                context?.let {
                    val editText = EditText(it)

                    MaterialAlertDialogBuilder(it, R.style.MyAlertDialogTheme)
                        .setTitle("Enter your description")
                        .setView(editText)
                        .setPositiveButton("Add") { _, _ ->
                            val text = editText.text.toString()

                            if (text.isNotEmpty()) {
                                viewModel.addToDo(text)
                            }
                        }
                        .setNegativeButton("Cancel", null)
                        .show()
                }
            }
        }
    }

    private fun setupObservers() {
        with(viewModel) {
            list.observe(viewLifecycleOwner, {
                toDoAdapter.submitList(null)
                toDoAdapter.submitList(it)

                binding.recycler.isVisible = true
                binding.error.isVisible = false
            })

            error.observe(viewLifecycleOwner, {
                binding.error.text = it

                binding.recycler.isVisible = false
                binding.error.isVisible = true
            })

            fetchTodo()
        }
    }

    override fun onClick(item: ToDo, view: View) {
        val menu = PopupMenu(context, view)
        menu.menu.add(Menu.NONE, 1, 1, "Update")
        menu.menu.add(Menu.NONE, 2, 2, "Delete")
        menu.setOnMenuItemClickListener {
            when (it.itemId) {
                1 -> {
                    viewModel.updateToDo(item)
                    true
                }
                2 -> {
                    viewModel.removeToDo(item)
                    true
                }
                else -> false
            }
        }
        menu.show()
    }
}