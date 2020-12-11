package io.github.iamraf.todo.data.model

data class ToDoRemoteModel(
    var id: String?,
    val description: String?,
    var completed: Boolean?
) {
    constructor() : this(null, null, null)
}