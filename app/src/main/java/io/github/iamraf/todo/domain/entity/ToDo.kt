package io.github.iamraf.todo.domain.entity

import com.google.firebase.firestore.Exclude

data class ToDo(
    @Exclude @get:Exclude val id: String,
    val description: String,
    var completed: Boolean
)