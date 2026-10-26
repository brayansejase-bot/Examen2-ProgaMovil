package org.ucb.appp1.tasks.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.ucb.appp1.tasks.domain.Task
import org.ucb.appp1.tasks.domain.repository.TaskRepository

class ObserveTasksUseCase(private val repository: TaskRepository) {
    operator fun invoke(): Flow<List<Task>> = repository.getTasks()
}
