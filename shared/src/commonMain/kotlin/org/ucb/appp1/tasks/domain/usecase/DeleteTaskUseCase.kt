package org.ucb.appp1.tasks.domain.usecase

import org.ucb.appp1.tasks.domain.repository.TaskRepository

class DeleteTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(taskId: String): Result<Unit> = repository.deleteTask(taskId)
}
