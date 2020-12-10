package io.github.iamraf.todo.presentation.todo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.iamraf.todo.domain.entity.ToDo
import io.github.iamraf.todo.usecase.FetchToDoUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ToDoViewModel @ViewModelInject constructor(private val fetchToDoUseCase: FetchToDoUseCase) : ViewModel() {
    private val _list = MutableLiveData<List<ToDo>>()
    val list: LiveData<List<ToDo>> = _list

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchTodo() = viewModelScope.launch {
        try {
            fetchToDoUseCase.getTodo().collect {
                _list.value = it
            }
        } catch (exception: Exception) {
            _error.value = exception.message
        }
    }
}