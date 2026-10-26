package org.ucb.appp1.tasks.domain.usecase

import org.ucb.appp1.tasks.domain.Task
import org.ucb.appp1.tasks.domain.repository.TaskRepository

class ToggleTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(task: Task): Result<Unit> = repository.toggleTask(task)
}
