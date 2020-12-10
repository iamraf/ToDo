package io.github.iamraf.todo.data.model

data class ToDoRemoteModel(
    val description: String?,
    val completed: Boolean?
) {
    constructor() : this(null, null)
}