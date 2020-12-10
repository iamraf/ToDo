package io.github.iamraf.todo.data

import io.github.iamraf.todo.data.model.ToDoRemoteModel
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    fun getTodo(): Flow<MutableList<ToDoRemoteModel>>
}