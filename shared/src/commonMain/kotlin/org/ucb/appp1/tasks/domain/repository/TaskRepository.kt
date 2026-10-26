package org.ucb.appp1.tasks.domain.repository

import kotlinx.coroutines.flow.Flow
import org.ucb.appp1.tasks.domain.Task

interface TaskRepository {
    fun getTasks(): Flow<List<Task>>
    fun getCompletedCount(): Flow<Int>
    suspend fun toggleTask(task: Task): Result<Unit>
    suspend fun addTask(title: String): Result<Unit>
    suspend fun deleteTask(taskId: String): Result<Unit>
}
