package io.github.iamraf.todo.data

import io.github.iamraf.todo.data.model.toTodo
import io.github.iamraf.todo.domain.ToDoDataSource
import io.github.iamraf.todo.domain.entity.ToDo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ToDoDataSourceImpl(private val repository: ToDoRepository) : ToDoDataSource {
    override fun getTodo(): Flow<List<ToDo>> {
        return repository.getTodo().map { list ->
            list.map {
                it.toTodo()
            }
        }
    }
}