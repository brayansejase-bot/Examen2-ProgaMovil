package org.ucb.appp1.tasks.data.repository

import kotlinx.coroutines.flow.Flow
import org.ucb.appp1.tasks.data.datasource.TaskFirestoreDataSource
import org.ucb.appp1.tasks.data.datasource.TaskRealtimeDataSource
import org.ucb.appp1.tasks.domain.Task
import org.ucb.appp1.tasks.domain.repository.TaskRepository

class TaskRepositoryImpl(
    private val firestoreDS: TaskFirestoreDataSource,
    private val realtimeDS: TaskRealtimeDataSource
) : TaskRepository {

    override fun getTasks(): Flow<List<Task>> = firestoreDS.observeTasks()

    override fun getCompletedCount(): Flow<Int> = realtimeDS.observeCompletedCount()

    override suspend fun toggleTask(task: Task): Result<Unit> {
        val newCompletedState = !task.completed
        val delta = if (newCompletedState) 1 else -1

        return try {
            firestoreDS.updateTaskStatus(task.id, newCompletedState)
            try {
                realtimeDS.incrementCounter(delta)
                Result.success(Unit)
            } catch (e: Exception) {
                try {
                    firestoreDS.updateTaskStatus(task.id, task.completed)
                } catch (_: Exception) {}
                Result.failure(Exception("Error en RTDB: ${e.message}"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error al actualizar Firestore: ${e.message}"))
        }
    }

    override suspend fun addTask(title: String): Result<Unit> {
        return try {
            firestoreDS.addTask(title)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("Error al agregar tarea: ${e.message}"))
        }
    }

    override suspend fun deleteTask(taskId: String): Result<Unit> {
        return try {
            firestoreDS.deleteTask(taskId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("Error al eliminar tarea: ${e.message}"))
        }
    }
}
