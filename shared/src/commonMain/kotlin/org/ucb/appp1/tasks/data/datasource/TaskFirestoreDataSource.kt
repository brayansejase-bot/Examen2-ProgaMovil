package org.ucb.appp1.tasks.data.datasource

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.ucb.appp1.tasks.domain.Task

expect fun getCurrentTimeMillis(): Long

class TaskFirestoreDataSource {
    private val firestore by lazy { Firebase.firestore }
    private val tasksCollection by lazy { firestore.collection("tareas") }

    fun observeTasks(): Flow<List<Task>> {
        return tasksCollection.snapshots.map { querySnapshot ->
            querySnapshot.documents.map { doc ->
                Task(
                    id = doc.id,
                    title = doc.get<String?>("title") ?: "",
                    completed = doc.get<Boolean?>("completed") ?: false,
                    timestamp = doc.get<Long?>("timestamp") ?: 0L
                )
            }
        }
    }

    suspend fun addTask(title: String) {
        val taskMap = mapOf(
            "title" to title,
            "completed" to false,
            "timestamp" to getCurrentTimeMillis()
        )
        tasksCollection.add(taskMap)
    }

    suspend fun updateTaskStatus(taskId: String, completed: Boolean) {
        tasksCollection.document(taskId).update("completed" to completed)
    }

    suspend fun deleteTask(taskId: String) {
        tasksCollection.document(taskId).delete()
    }
}
