package io.github.iamraf.todo.framework

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import io.github.iamraf.todo.data.ToDoRepository
import io.github.iamraf.todo.data.model.ToDoRemoteModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ToDoRepositoryImpl(private val firestore: FirebaseFirestore) : ToDoRepository {
    @ExperimentalCoroutinesApi
    override fun getTodo(): Flow<MutableList<ToDoRemoteModel>> = callbackFlow {
        var eventsCollection: CollectionReference? = null

        try {
            eventsCollection = firestore.collection("todo")
        } catch (e: Throwable) {
            e.printStackTrace()
            close(e)
        }

        val subscription = eventsCollection?.addSnapshotListener { snapshot, _ ->
            if (snapshot == null) {
                return@addSnapshotListener
            }

            try {
                val list = mutableListOf<ToDoRemoteModel>()

                snapshot.documents.forEach {
                    val model = it.toObject(ToDoRemoteModel::class.java)

                    if (model != null) {
                        list.add(model)
                    }
                }

                offer(list)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }

        awaitClose { subscription?.remove() }
    }
}