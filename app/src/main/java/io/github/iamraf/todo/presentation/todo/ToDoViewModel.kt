package io.github.iamraf.todo.presentation.todo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.iamraf.todo.domain.entity.ToDo
import io.github.iamraf.todo.usecase.AddToDoUseCase
import io.github.iamraf.todo.usecase.FetchToDoUseCase
import io.github.iamraf.todo.usecase.RemoveToDoUseCase
import io.github.iamraf.todo.usecase.UpdateToDoUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ToDoViewModel @ViewModelInject constructor(
    private val fetchToDoUseCase: FetchToDoUseCase,
    private val addToDoUseCase: AddToDoUseCase,
    private val removeToDoUseCase: RemoveToDoUseCase,
    private val updateToDoUseCase: UpdateToDoUseCase
) : ViewModel() {
    private val _list = MutableLiveData<List<ToDo>>()
    val list: LiveData<List<ToDo>> = _list

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchTodo() = viewModelScope.launch {
        try {
            fetchToDoUseCase.getTodo().collect {
                if (it.isNotEmpty()) {
                    _list.value = it
                } else {
                    _error.value = "No ToDo found"
                }
            }
        } catch (e: Exception) {
            _error.value = e.message
        }
    }

    fun addToDo(description: String) {
        addToDoUseCase.addToDo(ToDo("", description, false))
    }

    fun removeToDo(todo: ToDo) {
        removeToDoUseCase.removeToDo(todo)
    }

    fun updateToDo(todo: ToDo) {
        todo.completed = !todo.completed
        updateToDoUseCase.updateToDo(todo)
    }
}