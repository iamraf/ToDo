package io.github.iamraf.todo.usecase

import io.github.iamraf.todo.domain.ToDoDataSource
import io.github.iamraf.todo.domain.entity.ToDo
import kotlinx.coroutines.flow.Flow

class FetchToDoUseCase(private val dataSource: ToDoDataSource) {
    fun getTodo(): Flow<List<ToDo>> = dataSource.getTodo()
}