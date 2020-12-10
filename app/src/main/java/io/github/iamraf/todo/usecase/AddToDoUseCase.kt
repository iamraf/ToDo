package io.github.iamraf.todo.usecase

import io.github.iamraf.todo.domain.ToDoDataSource
import io.github.iamraf.todo.domain.entity.ToDo

class AddToDoUseCase(private val dataSource: ToDoDataSource) {
    fun addToDo(todo: ToDo) = dataSource.addToDo(todo)
}