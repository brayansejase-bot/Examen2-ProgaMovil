package org.ucb.appp1.tasks.domain.usecase

import org.ucb.appp1.tasks.domain.repository.TaskRepository

class AddTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(title: String): Result<Unit> = repository.addTask(title)
}
