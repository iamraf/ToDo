package io.github.iamraf.todo.domain.entity

data class ToDo(
    val id: String,
    val description: String,
    var completed: Boolean
)