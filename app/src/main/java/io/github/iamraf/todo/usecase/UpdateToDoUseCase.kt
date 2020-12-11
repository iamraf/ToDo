package io.github.iamraf.todo.usecase

import io.github.iamraf.todo.domain.ToDoDataSource
import io.github.iamraf.todo.domain.entity.ToDo

class UpdateToDoUseCase(private val dataSource: ToDoDataSource) {
    fun updateToDo(todo: ToDo) = dataSource.updateToDo(todo)
}