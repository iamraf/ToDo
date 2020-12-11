package io.github.iamraf.todo.usecase

import io.github.iamraf.todo.domain.ToDoDataSource
import io.github.iamraf.todo.domain.entity.ToDo

class RemoveToDoUseCase(private val dataSource: ToDoDataSource) {
    fun removeToDo(todo: ToDo) = dataSource.removeToDo(todo)
}