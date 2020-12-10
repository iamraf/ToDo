package io.github.iamraf.todo.data.model

import io.github.iamraf.todo.domain.entity.ToDo

fun ToDoRemoteModel.toTodo(): ToDo {
    return ToDo(
        id = id ?: throw Exception(),
        description = description ?: "-",
        completed = completed ?: false
    )
}