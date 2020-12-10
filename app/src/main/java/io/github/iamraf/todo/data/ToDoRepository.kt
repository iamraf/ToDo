package io.github.iamraf.todo.data

import io.github.iamraf.todo.data.model.ToDoRemoteModel
import io.github.iamraf.todo.domain.entity.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    fun getTodo(): Flow<MutableList<ToDoRemoteModel>>
    fun addToDo(todo: ToDo)
    fun removeToDo(todo: ToDo)
    fun updateToDo(todo: ToDo)
}