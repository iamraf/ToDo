package io.github.iamraf.todo.domain

import io.github.iamraf.todo.domain.entity.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoDataSource {
    fun getTodo(): Flow<List<ToDo>>
    fun addToDo(todo: ToDo)
    fun removeToDo(todo: ToDo)
    fun updateToDo(todo: ToDo)
}